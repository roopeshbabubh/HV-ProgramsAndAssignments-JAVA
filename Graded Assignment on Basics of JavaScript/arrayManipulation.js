const customerData = [
    { customerID: 1, customerName: 'John', totalBillingAmount: 2500 },
    { customerID: 2, customerName: 'Jane', totalBillingAmount: 3500 },
    { customerID: 3, customerName: 'Doe', totalBillingAmount: 4000 }
  ];
  
  // Filter customers with totalBillingAmount > 3000
  const selectedCustomers = customerData.filter(customer => customer.totalBillingAmount > 3000);
  
  // Map and print the selected customer names and purchase details
  selectedCustomers.map(customer => {
    console.log(`${customer.customerName} - Total Billing Amount: Rs. ${customer.totalBillingAmount}`);
  });
  
  