import java.net.*; 
import java.io.*; 
import java.util.*; 
class DCClientHandler implements Runnable 
{ 
	private MulticastSocket socket; 
	private InetAddress group; 
	private int port; 
	private static final int MAX_LEN = 1000; 
	DCClientHandler(MulticastSocket socket, InetAddress group, int port) 
	{ 
		this.socket = socket; 
		this.group = group; 
		this.port = port; 
	} 
	
	@Override
	public void run() 
	{ 
		while(!DistributedChat.finished) 
		{ 
				byte[] buffer = new byte[DCClientHandler.MAX_LEN]; 
				DatagramPacket datagram = new
				DatagramPacket(buffer,buffer.length,group,port); 
				String message; 
			try
			{ 
				socket.receive(datagram); 
				message = new
				String(buffer,0,datagram.getLength(),"UTF-8"); 
				if(!message.startsWith(DistributedChat.name)) 
					System.out.println(message); 
			} 
			catch(IOException e) 
			{ 
				System.out.println("Socket closed!"); 
			} 
		} 
	} 
}
