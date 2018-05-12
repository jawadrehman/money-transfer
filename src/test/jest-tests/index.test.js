const fetch = require("isomorphic-fetch");

const createAccount = accountName => {
  return fetch("http://localhost:8081/accounts", {
    method: "PUT",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: `accountName=${accountName}`
  }).then(response => response.json());
};

const deposit = (accountId, amount) => {
  return fetch(
    `http://localhost:8081/accounts/${accountId}/deposit/${amount}`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    }
  ).then(response => response.json());
};

const transfer = (fromId, toId, amount) => {
  return fetch(`http://localhost:8081/transfer/${fromId}/${toId}/${amount}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  }).then(response => response.text());
};

test("api test ", () => {
  createAccount("test a").then(data => {
    Object.keys(data).forEach(item => {
      const props = data[item][0];

      expect(props).toHaveProperty("amount", 0);
      expect(props).toHaveProperty("accountName", "test a");

      //test depositing of money into this account
      deposit(item, 100).then(deposited => {
        console.log("depsoited", deposited);
        expect(deposited).toHaveProperty("amount", 100);

        createAccount("test b").then(data => {
          Object.keys(data).forEach(itemB => {
            transfer(item, itemB, 50).then(data => {
              expect(data).toBe("successful transfer");
            });
          });
        });
      });
    });
  });
});
