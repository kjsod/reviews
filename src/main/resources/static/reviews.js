$(document).ready(function () {

    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get("id");

    fetch(`api/products/${productId}`)
        .then(res => res.json())
        .then(data => {
            $("h1").html(data.description);
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
            data.map(r =>
                `<tr>
                    <td>${r.review}</td>
                    <td>${r.stars}</td></tr>`
            ).join(''));
        }).catch(err => {
            console.log("Feil",err);
            table.html(`<tr><td>Feil</td></tr>`)
    });
})