<%-- 
    Document   : kontrak_melaka
    Created on : Jun 7, 2010, 1:18:19 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.lelong.PerintahJualMelakaActionBean">

    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <div align="justify">
        <fieldset class="aras1">
            <legend>
                Kontrak
            </legend><br>

            <p align="center" style="font-size: 16pt;font-family:times;">
                <b>KONTRAK</b><br>
            </p><br><br>

            <p style="font-size: 11pt">
                <b>MEMORANDUM:- </b>
                Semua jualan dengan Lelongan Awam pada <b><fmt:formatDate value="${actionBean.lelongan.tarikhLelong}" pattern="dd MMMM yyyy"/></b>
                mengenai tanah yang terkandung dalam butir butir yang tersebut diatas
                <b>${actionBean.hakmilik.lot.nama}&nbsp;${actionBean.hakmilik.noLot},${actionBean.hakmilik.cawangan.name},&nbsp;${actionBean.hakmilik.daerah.nama},&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama}</b>.
                Nama <b>${actionBean.lelongan.pembida.nama}</b>
                (No.Kad Pengenalan Lain) <b>${actionBean.lelongan.pembida.noPengenalan}</b> penawar tertinggi yang
                diistiharkan sebagai pembeli tanah tersebut dengan harga sejumlah <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelongan.hargaBidaan}"/></b>
                telah membayar bayaran kepada<b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}&nbsp;(${i.jenis.nama})
                        </c:if>&nbsp;</c:forEach></b>sebanyak <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelongan.deposit}"/></b>
                sebagai deposit dan bersetuju membayar baki jualan pembelian dan menyempurnakan pembelian tersebut
                mengikut syarat-syarat jualan dan Pemegang Gadaian mengaku menerima wang deposit tersebut.
            </p><br><br>

            <p style="font-size: 11pt">
                <b>
                    HARGA PEMBELIAN &emsp;&emsp;&emsp; RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelongan.hargaBidaan}"/><br><br>
                    DEPOSIT         &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelongan.deposit}"/><br><br>
                    BAKI            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;   RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelongan.baki}"/><br><br>
                    NO TELEFON      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; ${actionBean.lelongan.pembida.noTelefonBimbit} <br><br>
                </b>
            </p>


        </fieldset>

    </div>
</s:form>