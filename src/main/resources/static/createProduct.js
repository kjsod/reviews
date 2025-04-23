$(document).ready(function () {

    const form = $('#productForm');



    form.submit(function (e) {
        e.preventDefault();

        if ($('#description').val().trim() === "") {
            alert("Fields cannot be empty");
        } else {

            const product = {
                description: $('#description').val()
            };


            fetch("../api/products", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(product)
            })
                .then(async res => {
                    if (!res.ok) {
                        const errorText = await res.text();
                        throw new Error(errorText);
                    }
                    return res.json();
                })
                .then(data => {
                    console.log("Product created", data);
                    alert("Product created");
                    window.location.href = `/products`;
                })
                .catch(error => {
                    alert(error.message);
                });

        }
    });
})