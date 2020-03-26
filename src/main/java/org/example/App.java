package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        LOGGER.trace("======trace");
        LOGGER.debug("======debug");
        LOGGER.info("======info");
        LOGGER.warn("======warn");
        LOGGER.error("======error");
        System.out.println( "Hello World!" );
    }
}
