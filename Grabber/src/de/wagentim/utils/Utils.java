package de.wagentim.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Utils
{
	public static String getCurrentPath()
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		return s;
	}
	
	public static String getAbsolutePath(String file)
	{
		return getCurrentPath() + File.separator + file;
	}
}
