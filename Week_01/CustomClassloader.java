import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomClassloader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> cls = findLoadedClass(name);
        if (cls != null) {
            return cls;
        }
        String path = "";
        try (InputStream is = new FileInputStream(path + name + ".xlass"); ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            int b;
            while ((b = is.read()) != -1) {
                baos.write(255 - b);
            }
            byte[] bytes = baos.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static void main(String[] args) {
        CustomClassloader customClassloader = new CustomClassloader();
        Class clazz;
        try {
            clazz = customClassloader.findClass("Hello");
            Method hello = clazz.getMethod("hello");
            hello.invoke(clazz.newInstance(), (Object[]) null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}