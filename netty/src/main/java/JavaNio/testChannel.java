package JavaNio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: zxc12788.
 * Date: 2015/8/27.
 * Description:
 */
public class testChannel {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(8);
        //将channel写到buffer
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            //将buffer的写模式切换到读模式
            buf.flip();
            while(buf.hasRemaining()){
                System.out.println((char) buf.get()+"  "+buf.position()+"  "+buf.limit());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
