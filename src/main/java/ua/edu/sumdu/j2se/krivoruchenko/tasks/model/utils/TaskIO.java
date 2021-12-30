package ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils;

import com.google.gson.*;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TaskIO {
    private static final Logger logger = Logger.getLogger(TaskIO.class);

    // метод, що записує задачі зі списку у потік у бінарному форматі
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream outputStream = new DataOutputStream(out)){
            outputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                if (task != null) {
                    outputStream.writeInt(task.getTitle().length());
                    outputStream.writeUTF(task.getTitle());
                    outputStream.writeBoolean(task.isActive());
                    outputStream.writeInt(task.getRepeatInterval());
                    if (task.isRepeated()) {
                        outputStream.writeInt(task.getStartTime().getNano());
                        outputStream.writeInt(task.getEndTime().getNano());
                    } else {
                        outputStream.writeInt(task.getTime().getNano());
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

    // метод, що зчитує задачі із потоку у даний список задач
    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream inputStream = new DataInputStream(in)){
            int numberOfTask = inputStream.readInt();
            for (int i = 0; i < numberOfTask; i++) {
                Task temp;
                inputStream.readInt();
                String name = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                if (interval > 0){
                    LocalDateTime start = LocalDateTime.ofEpochSecond(inputStream.readInt(), 0, ZoneOffset.UTC);
                    LocalDateTime end = LocalDateTime.ofEpochSecond(inputStream.readInt(),0,ZoneOffset.UTC);
                    temp = new Task(name, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofEpochSecond(inputStream.readInt(),0,ZoneOffset.UTC);
                    temp = new Task (name,time);
                }
                temp.setActive(active);
                tasks.add(temp);
            }
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

    // метод, що записує задачі із списку у файл
    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            write(tasks, fileOutputStream);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

    // метод, що зчитує задачі із файлу у список задач
    public static void readBinary (AbstractTaskList tasks, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            read(tasks, fileInputStream);
        } catch (FileNotFoundException e) {
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

    // метод, що записує задачі зі списку у потік в форматі JSON
    public static void write (AbstractTaskList tasks, Writer out) {
        AbstractTaskList tempList = new LinkedTaskList();
        tasks.getStream().filter(Objects::nonNull).forEach(tempList::add);
        Gson gson = new GsonBuilder()   //реєстрація серіалізатора (Gson викликає метод зворотнього виклику serialize(), коли зустічає поле вказаного типу.
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .setPrettyPrinting()
                .create();
        try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {
            gson.toJson(tempList, bufferedWriter);
            bufferedWriter.flush();
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

    // метод, що зчитує задачі із потоку у список
    public static void read(AbstractTaskList tasks, Reader in) {
        Gson gson = new GsonBuilder() //реєстрація десеріалізатора (Gson викликає метод зворотнього виклику deserialize(), коли зустічає поле вказаного типу.
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .create();
        AbstractTaskList tempList = new LinkedTaskList();
        try (BufferedReader bufferedReader = new BufferedReader(in)) {
            tempList = gson.fromJson(bufferedReader, LinkedTaskList.class);
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
        tempList.getStream().forEach(tasks::add);
    }

    // метод, що записує задачі у файл у форматі JSON
    public static void writeText(AbstractTaskList tasks, File file) {
        try (FileWriter fileWriter = new FileWriter(file)){
            write(tasks,fileWriter);
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

    // метод, що зчитує задачі із файлу
    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader fileReader = new FileReader(file)){
            read(tasks, fileReader);
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }


    // серіалізатор та десеріалізатор для обєктів LocalDateTime в задачах
    static class LocalDateTimeSerializer implements JsonSerializer < LocalDateTime >, JsonDeserializer < LocalDateTime > {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),formatter);
        }
    }
}
