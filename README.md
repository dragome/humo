# Humo Language

Humo is a programming language with a tiny interpreter implementation and the smallest set of operations for an imperative programming language.

This is an experimental language that uses very few concepts to perform Turing complete computations.


``` Java
/*
 * Humo Language 
 * Copyright (C) 2002-2010, Fernando Damian Petrola
 *
 * Distributable under GPL license.
 * See terms of license at gnu.org.
 */

package ar.net.fpetrola.humo;

import java.util.HashMap;
import java.util.Map;

public class HumoInterpreter
{
    protected Map<CharSequence, CharSequence> productions = new HashMap<CharSequence, CharSequence>();

    public int parse(StringBuilder sourcecode, int first)
    {
        int last = first, current = first;

        for (char currentChar; last < sourcecode.length() && (currentChar = sourcecode.charAt(last++)) != '}';)
        {
            if (currentChar == '{')
            {
                current = parse(sourcecode, last);
                productions.put(sourcecode.subSequence(first, last - 1), sourcecode.subSequence(last, current));
                last = first = ++current;
            }
            else
            {
                CharSequence production = productions.get(sourcecode.subSequence(current, last));
                if (production != null)
                {
                    StringBuilder value = new StringBuilder(production);
                    parse(value, 0);
                    sourcecode.replace(current, last, value.toString());
                    last = current += value.length();
                }
            }
        }

        return last - 1;
    }
}
```
