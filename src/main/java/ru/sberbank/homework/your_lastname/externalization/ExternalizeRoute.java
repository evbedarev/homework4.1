package ru.sberbank.homework.your_lastname.externalization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.List;

public class ExternalizeRoute implements Externalizable{
    List<City> cityList;
    String uuid;

    public ExternalizeRoute(List<City> cityList, String uuid) {
        this.cityList = cityList;
        this.uuid = uuid;
    }

    public ExternalizeRoute() {
    }


    public String getUuid() {
        return uuid;
    }

    public List<City> getCityList() {
        return cityList;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(cityList);
        out.writeObject(uuid);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        cityList = (List<City>) in.readObject();
        uuid = (String) in.readObject();

    }


}
