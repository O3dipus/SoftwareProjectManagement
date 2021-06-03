<template>
    <div class="about">
        <h1>Query for food</h1>
        <v-btn
            elevation="2"
            @click="test"
            class="d-flex justify-center text-center mx-auto align-center"
        >查询</v-btn>

        <v-simple-table>
            <template v-slot:default>
            <thead>
                <tr>
                <th class="text-left">
                    name
                </th>
                <th class="text-left">
                    measurement
                </th>
                <th class="text-left">
                    carbohydrate
                </th>
                <th class="text-left">
                    fat
                </th>
                <th class="text-left">
                    protein
                </th>
                <th class="text-left">
                    vitamin
                </th>
                </tr>
            </thead>
            <tbody>
                <tr
                v-for="item in foodList"
                :key="item.id"
                >
                <td>{{ item.name }}</td>
                <td>{{ item.measurement }}</td>
                <td>{{ item.carbohydrate }}</td>
                <td>{{ item.fat }}</td>
                <td>{{ item.protein }}</td>
                <td>{{ item.vitamin }}</td>
                </tr>
            </tbody>
            </template>
        </v-simple-table>
    </div>
</template>

<script>

export default {
    data: () => ({
        foodName:'',
        foodList: [{}]
    }),
    methods: {
    test(){
        this.$axios.get(
            'http://124.70.23.6:8080/api/v1/food')
        .then(res=>{
        this.foodList=res.data;

        if(res.data){
            console.log('成功');
        }
        else{
            console.log('失败');
        }
        })
        .catch(error =>console.log(error.data));
        }
    }
}
</script>

