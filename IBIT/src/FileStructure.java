import java.io.File;

public class FileStructure {
	private static File file;
	public FileStructure(String destination, String todayPassed, String string){
		if( true)
		{
			if( findFile( new File( destination), todayPassed,string) <= 0 )
			{
			}
		}
	}
	public static void main() {
	}
	public File getFile(){
		return file;
	}
	public static int findFile( File pDir, String word, String string  )
	{
		int count = 0;
		File[] arrFile = pDir.listFiles();
		File tmp;

		// First look into this folder
		try{
			for (int i=0; i<arrFile.length; i++){

				tmp = arrFile[i];
				//System.out.println(tmp.getName());
				if ( tmp.getName().contains(word)&&tmp.getName().contains(string))
				{
					//System.out.println(tmp.getName());
					//System.out.println(tmp.getAbsoluteFile());
					file=tmp.getAbsoluteFile();
					return 1;
					//count++;
				}
			}
		}catch(NullPointerException e){
			return 0;
		}
		//now look into sub folders
		for (int i=0; i<arrFile.length; i++)
		{
			tmp = arrFile[i];
			//System.out.println(tmp);
			if( tmp.isDirectory() )
			{
				findFile( tmp, word, string );
			}
		}
		return count;
	}
}