
# Bus-Reservation-System


Online bus reservation system is a project which provides an online portal for bus ticket reservation. This application allows customers to book bus tickets from anywhere and anytime. The customer can easily book and cancel their tickets at their comfort. The customer can view all the details of the seats, bus, and route along with pictures.




## Ticket related API

#### Book Tickets

```bash
  POST /user/tickets/book/{bus_id}/{numberOfSeats}
```

| Parameter | Type     
| :-------- | :------- 
| `bus_id` | `long`
| `numberOfSeats` | `int` 

#### Get all Tickets related to user

```bash
  GET /user/tickets
```

#### Get all Tickets

```bash
  GET /admin/tickets
```
#### Cancel Tickets

```bash
  DELETE /user/tickets/{id}/cancel
```

| Parameter | Type   | Description |  
| :-------- | :-------  | :-------
| `id` | `long` | Ticket id



## Bus related API

#### Add Bus

```bash
  POST /admin/addbus
```
Refer Bus model


#### Update Bus

```bash
  POST /admin/updateBus
```

#### Get all bus

```bash
  GET /admin/getBuses
```
#### Get Bus by ID

```bash
  GET /getBuses/{id}
```

| Parameter | Type   | Description |  
| :-------- | :-------  | :-------
| `id` | `long` | Bus ID

#### Check availability of bus 

```bash
  GET /user/check/{source}/{destination}/{date}
```

| Parameter | Type   | 
| :-------- | :-------  
| `source` | `string` |
| `destination` | `string` |
| `date` | `string` |


