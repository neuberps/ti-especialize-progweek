package br.tie.progweek.controller;

import br.tie.progweek.model.Client;
import br.tie.progweek.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/listClients")
    public ModelAndView listClients() {
        ModelAndView modelAndView = new ModelAndView("list-clients");
        List<Client> clients = clientService.list();
        int listSize = 0;
        if (!clients.isEmpty()) {
            modelAndView.addObject("list", clients);
            modelAndView.addObject("showlist", true);
            listSize = clients.size();
        }
        modelAndView.addObject("listSize", listSize);
        return modelAndView;
    }

    @RequestMapping(value = "/createClient")
    public ModelAndView createClient() {

        return new ModelAndView("client");
    }

    @RequestMapping(value = "/editClient")
    public ModelAndView editClient(String id) {
        ModelAndView modelAndView = new ModelAndView("client");
        log.info("editClient.id: " + id);
        Optional<Client> client = clientService.getClient(id);
        log.info("editClient.user: " + client.toString());
        if (client.isPresent()) {
            modelAndView.addObject("client", client.get());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveClient")
    public ModelAndView saveClient(Client client) {
        log.info("saveClient.id: " + client.getId());
        clientService.saveAndUpdate(client);
        return listClients();
    }

    @RequestMapping(value = "/deleteClient")
    public ModelAndView deleteClient(String id) {
        log.info("deleteClient.id: " + id);
        clientService.delete(id);
        return listClients();
    }

}
