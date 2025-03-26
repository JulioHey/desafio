import 'package:dio/dio.dart';
import 'package:exercicio5front/model/create_vehicle.dart';
import 'package:exercicio5front/model/vehicles.dart';

class VehicleProvider {
  Dio dio = Dio(BaseOptions(
    baseUrl: "http://localhost:8080",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Access-Control-Allow-Origin": "*"
    },
  ));

  Future<Vehicle> getVehicle(int id) async {
    Response response = await dio.get('/veiculos/$id');
    return Vehicle.fromJson(response.data);
  }

  Future<List<Vehicle>> getVehiclesByBrand(String brand) async {
    Response response =
        await dio.get('/veiculos', queryParameters: {'marca': brand});
    return (response.data as List).map((e) => Vehicle.fromJson(e)).toList();
  }

  Future<List<Vehicle>> getVehiclesByDecade(int decade) async {
    Response response = await dio.get('/veiculos/decade/$decade');
    return (response.data as List).map((e) => Vehicle.fromJson(e)).toList();
  }

  Future<Vehicle> createVehicle(CreateVehicle vehicle) async {
    Response response = await dio.post('/veiculos', data: vehicle.toJson());
    return Vehicle.fromJson(response.data);
  }

  Future<CreateVehicle> updateVehicle(int id, CreateVehicle vehicle) async {
    await dio.put('/veiculos/$id', data: vehicle.toJson());
    return vehicle;
  }

  Future<void> deleteVehicle(int id) async {
    await dio.delete('/veiculos/$id');
  }

  Future<List<Vehicle>> getVehiclesLastWeek() async {
    try {
      Response response = await dio.get('/veiculos/last-week');
      return (response.data as List).map((e) => Vehicle.fromJson(e)).toList();
    } catch (e) {
      return [];
    }
  }

  Future<int> getNotSoldVehicles() async {
    try {
      Response response = await dio.get('/veiculos/not-sold');
      return (response.data as List).length;
    } catch (e) {
      return 0;
    }
  }
}
