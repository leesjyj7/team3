<template>

    <v-data-table
        :headers="headers"
        :items="productInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ProductInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "productId", value: "productId" },
                { text: "name", value: "name" },
                { text: "spec", value: "spec" },
                { text: "qty", value: "qty" },
                { text: "price", value: "price" },
            ],
            productInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/productInfos'))

            temp.data._embedded.productInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.productInfo = temp.data._embedded.productInfos;
        },
        methods: {
        }
    }
</script>

