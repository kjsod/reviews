$(document).ready(function () {

    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get("id");
    let productName = "";

    fetch(`api/products/${productId}`)
        .then(res => res.json())
        .then(data => {
            $("h1").html(data.description);
            productName = data.description;
            $('#productid').val(data.description);
    }).catch(err => {
        console.log("Feil", err);
    });

    fetch(`api/reviews/product/${productId}`)
        .then(res => res.json())
        .then(data => {
            const table = $('#reviewTable');
            table.html(`<tr>
                                <th>Review</th>
                                <th>Stars</th>
                                </tr>`+
            data.map(r => {
                    const output =
                        `<span class="fa fa-star checked"></span>`.repeat(r.stars)+
                        `<span class="fa fa-star"></span>`.repeat(5-r.stars);
                    console.log(r.stars);

                    return `<tr>
                    <td>${r.review}</td>
                    <td>${output}</td></tr>`;

            }).join(''));
        }).catch(err => {
            console.log("Feil",err);
            table.html(`<tr><td>Feil</td></tr>`)
    });

    const updateForm = $('#updateForm');

    updateForm.submit(function (e) {
        e.preventDefault();
        const newname = $('#newname').val();

        if (newname.trim() === "") {
            alert("New name cannot be empty");
        } else {
            const updated = {
                id: productId,
                description: newname.trim()
            }

            fetch(`api/products/${productId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updated)
            })
                .then(res => {
                    if (!res.ok) {
                        throw new Error("Server responded with: " + res.status);
                    }
                    return res.text();

                })
                .then(data => {
                    console.log("Updated successfully");
                    alert("Updated successfully");
                    location.reload();
                })
                .catch(err => {
                    console.error("Feil");
                });
    }});

    const deletebtn = $('#deletebtn');

    deletebtn.click(function (e) {
        e.preventDefault();

        if (confirm(`Are you sure you want to delete this product: ${productName}`)) {

            fetch(`api/products/${productId}`, {
                method: 'DELETE'
            })
                .then(res => {
                    if (!res.ok) {
                        throw new Error(`Delete failed ${res.status}`);
                    }
                    return res.text();
                })
                .then(data => {
                    console.log(`Deleted product '${productName}' id: ${productId}`);
                    alert("Product deleted")
                    window.location.href = "/products";
                })
                .catch(err => {
                    console.error("Error",err);
                })

        } else {
            console.log("ok buddy, wont delete");
        }
    });


});