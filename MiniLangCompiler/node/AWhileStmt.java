/* This file was generated by SableCC (http://www.sablecc.org/). */

package MiniLangCompiler.node;

import java.util.*;
import MiniLangCompiler.analysis.*;

@SuppressWarnings("nls")
public final class AWhileStmt extends PStmt
{
    private PExp _l_;
    private final LinkedList<PStmt> _r_ = new LinkedList<PStmt>();

    public AWhileStmt()
    {
        // Constructor
    }

    public AWhileStmt(
        @SuppressWarnings("hiding") PExp _l_,
        @SuppressWarnings("hiding") List<?> _r_)
    {
        // Constructor
        setL(_l_);

        setR(_r_);

    }

    @Override
    public Object clone()
    {
        return new AWhileStmt(
            cloneNode(this._l_),
            cloneList(this._r_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAWhileStmt(this);
    }

    public PExp getL()
    {
        return this._l_;
    }

    public void setL(PExp node)
    {
        if(this._l_ != null)
        {
            this._l_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._l_ = node;
    }

    public LinkedList<PStmt> getR()
    {
        return this._r_;
    }

    public void setR(List<?> list)
    {
        for(PStmt e : this._r_)
        {
            e.parent(null);
        }
        this._r_.clear();

        for(Object obj_e : list)
        {
            PStmt e = (PStmt) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._r_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._l_)
            + toString(this._r_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._l_ == child)
        {
            this._l_ = null;
            return;
        }

        if(this._r_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._l_ == oldChild)
        {
            setL((PExp) newChild);
            return;
        }

        for(ListIterator<PStmt> i = this._r_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStmt) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
