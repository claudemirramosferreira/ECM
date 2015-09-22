function Home($resource, ecmRest) {
    return $resource(ecmRest + 'rest', {},{});
}