package br.com.fabioperettig.exceptions;

public class TipoChaveNaoEncontradaException extends Exception{

    private static final long serialVersiionUID = 1337L;

    public TipoChaveNaoEncontradaException(String msg){
        this(msg, null);
    }

    public TipoChaveNaoEncontradaException(String msg, Throwable e){
        super(msg, null);
    }

}
