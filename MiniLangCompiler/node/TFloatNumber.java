/* This file was generated by SableCC (http://www.sablecc.org/). */

package MiniLangCompiler.node;

import MiniLangCompiler.analysis.*;

@SuppressWarnings("nls")
public final class TFloatNumber extends Token
{
    public TFloatNumber(String text)
    {
        setText(text);
    }

    public TFloatNumber(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TFloatNumber(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFloatNumber(this);
    }
}
