/**
 * Utilitários AESTRON - Funções compartilhadas
 */

// Função para salvar dados com tratamento adequado de erros
async function salvarComSeguranca(url, dados, opcoes = {}) {
    const {
        metodo = 'POST',
        modalId = null,
        formId = null,
        callbackSucesso = null,
        callbackErro = null,
        mensagemSucesso = 'Salvo com sucesso!',
        mensagemErro = 'Erro ao salvar. Tente novamente.'
    } = opcoes;

    try {
        const response = await fetch(url, {
            method: metodo,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });

        // Verificar se a resposta foi bem-sucedida
        if (!response.ok) {
            const errorText = await response.text();
            console.error('Erro do servidor:', errorText);
            throw new Error(`Erro ${response.status}: ${response.statusText}`);
        }

        // Tentar parsear o JSON de resposta
        let resultado;
        try {
            resultado = await response.json();
        } catch (e) {
            resultado = null;
        }

        // Fechar modal se especificado
        if (modalId) {
            const modalElement = document.getElementById(modalId);
            if (modalElement) {
                const modalInstance = bootstrap.Modal.getInstance(modalElement);
                if (modalInstance) {
                    modalInstance.hide();
                }
            }
        }

        // Resetar formulário se especificado
        if (formId) {
            const form = document.getElementById(formId);
            if (form) {
                form.reset();
            }
        }

        // Exibir mensagem de sucesso
        exibirMensagem('success', mensagemSucesso);

        // Callback de sucesso
        if (callbackSucesso) {
            await callbackSucesso(resultado);
        }

        return resultado;

    } catch (error) {
        console.error('Erro ao salvar:', error);
        
        // Exibir mensagem de erro
        exibirMensagem('danger', mensagemErro);

        // Callback de erro
        if (callbackErro) {
            callbackErro(error);
        }

        throw error;
    }
}

// Função para exibir mensagens toast ou alert
function exibirMensagem(tipo, mensagem) {
    // Verificar se existe uma área de mensagens
    let msgArea = document.getElementById('mensagem-area');
    
    if (!msgArea) {
        // Criar área de mensagens se não existir
        msgArea = document.createElement('div');
        msgArea.id = 'mensagem-area';
        msgArea.style.cssText = 'position: fixed; top: 20px; right: 20px; z-index: 9999; max-width: 400px;';
        document.body.appendChild(msgArea);
    }

    // Criar alerta
    const alert = document.createElement('div');
    alert.className = `alert alert-${tipo} alert-dismissible fade show`;
    alert.innerHTML = `
        ${mensagem}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;

    msgArea.appendChild(alert);

    // Remover após 5 segundos
    setTimeout(() => {
        alert.classList.remove('show');
        setTimeout(() => alert.remove(), 150);
    }, 5000);
}

// Formatador de moeda brasileiro
const fmt = new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
});

// Função para formatar valores monetários
function formatarMoeda(valor) {
    if (valor === null || valor === undefined || isNaN(valor)) {
        return fmt.format(0);
    }
    return fmt.format(valor);
}

// Formatador de data brasileiro
function formatarData(dataISO) {
    if (!dataISO) return '';
    const data = new Date(dataISO + 'T00:00:00');
    return data.toLocaleDateString('pt-BR');
}

// Debounce para busca/filtros
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}
