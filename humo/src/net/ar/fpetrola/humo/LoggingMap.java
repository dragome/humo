package net.ar.fpetrola.humo;

import java.util.HashMap;

@SuppressWarnings("serial")
public class LoggingMap extends HashMap<String, String>
{
    private ParserListener parserListener;

    public LoggingMap(ParserListener parserListener)
    {
        this.parserListener = parserListener;
    }

    public synchronized String get(Object key)
    {
        String o = super.get(key);
        log("uso: " + (String) key + " = " + (String) o + "");
        return o;
    }
    public synchronized String remove(Object key)
    {
        String o = super.remove(key);
        log((String) key + "{}");
        return o;
    }
    public synchronized String put(String key, String value)
    {
        parserListener.endProductionCreation(key, value);

        log((String) key);
        log("{");
        log("\t" + (String) value);
        log("}\n");
        String o = super.put(key, value);
        return o;
    }

    public void log(String t)
    {
        StringBuffer sb = new StringBuffer();
        //        for (int primero = 0; primero < parser.getDepth(); primero++)
        //            sb.append("\t");

        System.out.println(sb.toString() + t);
    }

}