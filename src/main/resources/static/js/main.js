class RequisicaoNovaOferta {
	constructor(pedido) {
		this.pedidoId = pedido.id;
		this.valor = pedido.valorNegociado;
		this.dataEntrega = pedido.dataEntrega;
		this.comentario = pedido.comentario;
	}
}

function onLoad() {
	var ofertas = new Vue({
		el: '#ofertas',
		data: {
			pedidos: []
		},
		mounted() {
			axios
				.get('http://localhost:8080/api/pedidos/aguardando')
				.then(response => {
					response.data.forEach(pedido => {
						pedido.ofertaEnviada = false;
						pedido.errors = {
							valor:'',
							dataEntrega:''
						}
					});
					this.pedidos = response.data;
				})
		},
		methods: {
			enviarOferta: function(pedido) {
				pedido.errors = {
							valor:'',
							dataEntrega:''
						}
				axios
					.post('http://localhost:8080/api/ofertas', new RequisicaoNovaOferta(pedido))
					.then(response => ((pedido.ofertaEnviada = true)))
					.catch(error => {
						error.response.data.errors.forEach(error => {
							pedido.errors[error.field] = error.defaultMessage;
						})
					})
					;
			}
		}
	});
}
