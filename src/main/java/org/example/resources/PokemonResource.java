package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.Main;
import org.example.model.Pokemon;
import org.example.repositories.PokemonRepository;

import java.util.List;
import java.util.Optional;


@Path("pokemon")
public class PokemonResource {

    PokemonRepository pokemonRepository = new PokemonRepository();

    @GET
    public Response getPokemons(){
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        if (pokemonList.isEmpty()){
            Main.LOGGER.info("404 - Pokemon NOT FOUND");
            return Response.status(404).entity("Pokemon not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(pokemonList).build();
    }

    @GET
    @Path("{id}")
    public Response getPokemonById(@PathParam("id")int id){
        Optional<Pokemon> pokemon = pokemonRepository.findPokemonById(id);
        if (pokemon.isEmpty()){
            Main.LOGGER.info("404 - Pokemon NOT FOUND");
            return Response.status(404).entity("Pokemon not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(pokemon).build();
    }

    @POST
    public Response createPokemon(Pokemon pokemon){
        if (pokemon == null){
            Main.LOGGER.info("404 - Pokemon can not be null ");
            return Response.status(404).entity("Pokemon can not be null").build();
        }
        pokemonRepository.cretePokemon(pokemon);
        Main.LOGGER.info("[POST] - 201 - Created");
        return Response.status(201).entity(pokemon).build();
    }

    @PUT
    @Path("{id}")
    public Response updatePokemon(@PathParam("id")int id, Pokemon pokemon){
        if (pokemonRepository.findPokemonById(id).isPresent()){
            pokemon.setId(id);
            pokemonRepository.updatePokemon(pokemon);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(pokemon).build();
        }
        Main.LOGGER.info("404 - Pokemon not found ");
        return Response.status(404).entity("Pokemon not found").build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePokemon(@PathParam("id")int id){
        pokemonRepository.deletePokemon(id);
        Main.LOGGER.info("[DELETE] - 204 - No content");
        return Response.noContent().build();
    }
}
