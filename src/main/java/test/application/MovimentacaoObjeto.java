package test.application;

public class MovimentacaoObjeto {
    private int conta_id;
    private String tipo;
    private String data_transacao;
    private String data_pagamento;
    private String descricao;
    private String envolvido;
    private int valor;
    private String conta;
    private boolean status;

    public MovimentacaoObjeto(int id, String tipoMovimentacao, String dataMovimentacao, String dataPagamento, String descricao, String interessado, int valor, String conta, boolean situacao){
        this.conta_id = id;
        this.tipo = tipoMovimentacao;
        this.data_transacao = dataMovimentacao;
        this.data_pagamento = dataPagamento;
        this.descricao = descricao;
        this.envolvido = interessado;
        this.valor = valor;
        this.conta = conta;
        this.status = situacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData_transacao() {
        return data_transacao;
    }

    public void setData_transacao(String data_transacao) {
        this.data_transacao = data_transacao;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEnvolvido() {
        return envolvido;
    }

    public void setEnvolvido(String envolvido) {
        this.envolvido = envolvido;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
