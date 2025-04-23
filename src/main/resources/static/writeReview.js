$(document).ready(function () {

    const select = $('#selectProduct');

    fetch("/api/products")
        .then(res => res.json())
        .then(data => {
            data.forEach(r => {
                select.append(
                    `<option value="${r.id}">${r.description}</option>`
                );
            });
    });



    const form = $('#reviewForm');
    form.submit(function(e) {
        e.preventDefault();

        if ($('#review').val().trim() === "" || $('#stars').val() === "") {
            alert("Fields cannot be empty");
        } else {
            const review = {
                review: $('#review').val(),
                stars: $('#stars').val(),
                productid: $('#selectProduct').val()
            };

            fetch("http://localhost:8080/api/reviews", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(review)
            })
                .then(res => {
                    res.text();
                })
                .then(data => {
                    console.log('Review opprettet', data);
                    alert("Review successfully created");
                    window.location.href = `/reviews?id=${$('#selectProduct').val()}`;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    });
});