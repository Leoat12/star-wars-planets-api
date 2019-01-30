package br.com.leoat.starwarsapi.exception;

/**
 * Exceção lançada quando um recurso não é encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource with id/name: " + id.toString() + " not found.");
    }

}
