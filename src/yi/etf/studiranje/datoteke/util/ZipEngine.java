package yi.etf.studiranje.datoteke.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Средство помоћу кога се могу запаковати датотеке и директоријуми. 
 * @author Computer
 * @version 1.0
 */
public class ZipEngine {
	private File file; 
	private List<String> fileList = new ArrayList<>(); 
	private String zipName; 
	private OutputStream output; 
	
	public ZipEngine(File file) {
		this.file = file; 
		this.zipName = file.getName()+".zip"; 
		this.output = new ByteArrayOutputStream(); 
	}
	
	public File getFile() {
		return file; 
	}
	
	public ZipEngine setFile(File file) {
		this.file = file;
		return this; 
	}
	
	
	public String getZipName() {
		return zipName;
	}

	public void setZipName(String zipName) {
		this.zipName = zipName;
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}

	public void zip() {
		fileList.clear(); 
		generateFileList(file);
		zipIt(); 
	}
	
	private void zipIt() {
        byte[] buffer = new byte[1024];
        String source = file.getName();
        OutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = output;
            zos = new ZipOutputStream(fos);

            FileInputStream in = null;

            for (String file: this.fileList) {
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(this.file.getPath() + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateFileList(File node) {
    	if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }

    private String generateZipEntry(String file) {
        return file.substring(this.file.getPath().length() + 1, file.length());
    }
}
