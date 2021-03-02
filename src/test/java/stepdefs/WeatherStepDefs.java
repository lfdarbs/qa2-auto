package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.util.Map;

public class WeatherStepDefs {
    private int cityId;
    private WeatherResponse response;

    @Given("city id: {int}")
    public void set_city_id(int cityId) {
        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.requestWeather(cityId);
    }

    @Then("lon is: {double}")
    public void check_lon(double lon) {
        Assertions.assertEquals(lon, response.getCoord().getLon(), "Wrong Lon!");
    }

    @And("lat is: {double}")
    public void check_lat(double lat) {
        Assertions.assertEquals(lat, response.getCoord().getLat(), "Wrong Lat");
    }

    @Then("weather is:")
    public void check_weather(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("id")),response.getWeathers().get(0).getId(), "Wrong weather ID");
        Assertions.assertEquals(params.get("main"),response.getWeathers().get(0).getMain(), "Wrong Weather...");
        Assertions.assertEquals(params.get("description"),response.getWeathers().get(0).getDescription(), "Wrong description");
        Assertions.assertEquals(params.get("icon"),response.getWeathers().get(0).getIcon(), "Wrong description");
    }

    @And("base is: {string}")
    public void baseIs(String base) {
        Assertions.assertEquals(base, response.getBase(), "Wrong base");
    }


    @And("main is:")
    public void mainIs(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("temp")),response.getMain().getTemp(), "Wrong temperature");
        Assertions.assertEquals(Integer.parseInt(params.get("pressure")),response.getMain().getPressure(), "Wrong pressure");
        Assertions.assertEquals(Integer.parseInt(params.get("humidity")),response.getMain().getHumidity(), "Wrong humidity");
        Assertions.assertEquals(Integer.parseInt(params.get("temp_min")),response.getMain().getTemp_min(), "Wrong temp_min");
        Assertions.assertEquals(Integer.parseInt(params.get("temp_max")),response.getMain().getTemp_max(), "Wrong temp_max");
    }


    @And("visibility: {int}")
    public void visibility(int visibility) {
        Assertions.assertEquals(visibility, response.getVisibility().getVisibility(),"Wrong visibility");
    }


    @And("wind is:")
    public void windIs(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("speed")),response.getWind().getSpeed(), "Wrong speed");
        Assertions.assertEquals(Integer.parseInt(params.get("deg")),response.getWind().getDeg(), "Wrong deg");
    }


    @And("clouds is:")
    public void cloudsIs(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("all")),response.getClouds().getAll(), "Wrong clouds");
    }


    @And("dt is: {int}")
    public void dtIs(int dt) {
        Assertions.assertEquals(dt, response.getDt().getDt(), "Dt is wrong");
    }

    @And("sys is:")
    public void sysIs(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("type")),response.getSys().getType(), "Wrong type");
        Assertions.assertEquals(Integer.parseInt(params.get("id")),response.getSys().getId(), "Wrong id");
        Assertions.assertEquals(Integer.parseInt(params.get("message")),response.getSys().getMessage(), "Wrong message");
        Assertions.assertEquals(params.get("country"),response.getSys().getCountry(), "Wrong country");
        Assertions.assertEquals(Integer.parseInt(params.get("sunrise")),response.getSys().getSunrise(), "Wrong sunries");
        Assertions.assertEquals(Integer.parseInt(params.get("sunset")),response.getSys().getSunset(), "Wrong sunset");
    }


    @And("id is: {int}")
    public void idIs(int id) {
        Assertions.assertEquals(id, response.getId().getId(),"Wrong id");
    }

    @And("name is: {string}")
    public void nameIs(String name) {
        Assertions.assertEquals(name, response.getName(), "Wrong name");
    }


    @And("cod is: {int}")
    public void codIs(int cod) {
        Assertions.assertEquals(cod, response.getCod().getCod(), "Wrong cod");
    }
}
