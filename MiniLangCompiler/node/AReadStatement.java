/* This file was generated by SableCC (http://www.sablecc.org/). */

package MiniLangCompiler.node;

import MiniLangCompiler.analysis.*;

@SuppressWarnings("nls")
public final class AReadStatement extends PStatement
{
    private TRead _read_;
    private TId _id_;
    private TSemicolon _semicolon_;

    public AReadStatement()
    {
        // Constructor
    }

    public AReadStatement(
        @SuppressWarnings("hiding") TRead _read_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setRead(_read_);

        setId(_id_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AReadStatement(
            cloneNode(this._read_),
            cloneNode(this._id_),
            cloneNode(this._semicolon_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAReadStatement(this);
    }

    public TRead getRead()
    {
        return this._read_;
    }

    public void setRead(TRead node)
    {
        if(this._read_ != null)
        {
            this._read_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._read_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public TSemicolon getSemicolon()
    {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(this._semicolon_ != null)
        {
            this._semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._read_)
            + toString(this._id_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._read_ == child)
        {
            this._read_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._read_ == oldChild)
        {
            setRead((TRead) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}