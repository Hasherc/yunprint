package com.qming.yunprint.service;

import com.qming.yunprint.dao.TicketDao;
import com.qming.yunprint.model.Ticket;
import com.qming.yunprint.model.UserHolder;
import com.qming.yunprint.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-21:30
 */
@Service
public class TicketService {
    @Autowired
    TicketDao ticketDao;
    @Autowired
    UserHolder userHolder;
    public String newTicket(int userId){
        Ticket ticket = new Ticket();
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setOwnerId(userId);
        ticket.setTicket(UUIDUtil.getUUID());
        ticket.setStatus(0);
        ticketDao.insertTicket(ticket);
        return ticket.getTicket();
    }

    public void setTicketExpired(String ticket){
        if (userHolder.get() != null){
            userHolder.remove();
        }
        ticketDao.updateTicketExpired(ticket);
    }
}
