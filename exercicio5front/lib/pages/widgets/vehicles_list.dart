import 'package:exercicio5front/model/vehicles.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class VehicleRow extends StatelessWidget {
  final Vehicle vehicle;

  const VehicleRow({super.key, required this.vehicle});

  @override
  Widget build(BuildContext context) {
    return Padding(
      key: Key("vehicle-${vehicle.id.toString()}"),
      padding: const EdgeInsets.all(10),
      child: GestureDetector(
        onTap: () {
          Get.offNamed("/edit/${vehicle.id}");
        },
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
                child: Text(vehicle.veiculo,
                    style: const TextStyle(
                        fontSize: 18, fontWeight: FontWeight.bold))),
            Text(vehicle.marca.name),
            Text(vehicle.ano.toString()),
            Expanded(
                child:
                    Text(vehicle.descricao, overflow: TextOverflow.ellipsis)),
            Text(vehicle.cor),
            Text(
              vehicle.vendido ? "Sold" : "Available",
              style: TextStyle(
                color: vehicle.vendido ? Colors.red : Colors.green,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class VehicleList extends StatelessWidget {
  final List<Vehicle> vehicles;

  const VehicleList({super.key, required this.vehicles});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: vehicles
          .map((vehicle) => VehicleRow(
                vehicle: vehicle,
              ))
          .toList(),
    );
  }
}
