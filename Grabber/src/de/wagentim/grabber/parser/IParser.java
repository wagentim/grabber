package de.wagentim.grabber.parser;

public interface IParser
{
	IParser getParser(int index);
	Object parser(Object object);
}
