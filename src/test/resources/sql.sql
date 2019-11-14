select * from carservice.servicesprice;
select * from carservice.companies;
select o.name, s.serviceName, sp.cost from  servicesprice sp  LEFT JOIN offices o on o.`officeID`=sp.`office__ID` LEFT JOIN services s on s.serviceID=sp.`service__ID`;
