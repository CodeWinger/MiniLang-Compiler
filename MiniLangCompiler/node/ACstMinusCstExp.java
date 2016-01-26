/* This file was generated by SableCC (http://www.sablecc.org/). */

package MiniLangCompiler.node;

import MiniLangCompiler.analysis.*;

@SuppressWarnings("nls")
public final class ACstMinusCstExp extends PCstExp
{
    private PCstExp _cstExp_;
    private TMinus _minus_;
    private PFactor _factor_;

    public ACstMinusCstExp()
    {
        // Constructor
    }

    public ACstMinusCstExp(
        @SuppressWarnings("hiding") PCstExp _cstExp_,
        @SuppressWarnings("hiding") TMinus _minus_,
        @SuppressWarnings("hiding") PFactor _factor_)
    {
        // Constructor
        setCstExp(_cstExp_);

        setMinus(_minus_);

        setFactor(_factor_);

    }

    @Override
    public Object clone()
    {
        return new ACstMinusCstExp(
            cloneNode(this._cstExp_),
            cloneNode(this._minus_),
            cloneNode(this._factor_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACstMinusCstExp(this);
    }

    public PCstExp getCstExp()
    {
        return this._cstExp_;
    }

    public void setCstExp(PCstExp node)
    {
        if(this._cstExp_ != null)
        {
            this._cstExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cstExp_ = node;
    }

    public TMinus getMinus()
    {
        return this._minus_;
    }

    public void setMinus(TMinus node)
    {
        if(this._minus_ != null)
        {
            this._minus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._minus_ = node;
    }

    public PFactor getFactor()
    {
        return this._factor_;
    }

    public void setFactor(PFactor node)
    {
        if(this._factor_ != null)
        {
            this._factor_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._factor_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._cstExp_)
            + toString(this._minus_)
            + toString(this._factor_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._cstExp_ == child)
        {
            this._cstExp_ = null;
            return;
        }

        if(this._minus_ == child)
        {
            this._minus_ = null;
            return;
        }

        if(this._factor_ == child)
        {
            this._factor_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._cstExp_ == oldChild)
        {
            setCstExp((PCstExp) newChild);
            return;
        }

        if(this._minus_ == oldChild)
        {
            setMinus((TMinus) newChild);
            return;
        }

        if(this._factor_ == oldChild)
        {
            setFactor((PFactor) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}