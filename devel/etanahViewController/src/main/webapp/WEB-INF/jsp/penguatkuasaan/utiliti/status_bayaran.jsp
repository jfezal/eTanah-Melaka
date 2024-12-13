<%-- 
    Document   : status_bayaran
    Created on : Apr 24, 2014, 2:12:35 PM
    Author     : Tengku.Fauzan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>

<script language="javascript" type="text/javascript">


</script>

<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiStatusBayaranActionBean" name="form1">
    <s:messages/>
    <s:errors/>
  

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Carian Status Bayaran Kompaun</legend>
            <br>
            <p><label>Id Permohonan:</label>
                <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchKompaun" value="Cari" class="btn"/>
            </p>

             <p>
                <label>Id Permohonan :</label>
                ${actionBean.permohonan.idPermohonan} &nbsp;
                <s:hidden name="idPermohonan" id="idPermohonanCarian"/>
             </p>
        
             <p>
                 <label for="Urusan">Urusan :</label>
                 ${actionBean.permohonan.kodUrusan.nama} &nbsp;
       
             </p>
            
            <div class="content" align="center">

                <br/>
                <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">
                        ${line_rowNum}
                    </display:column>
                    
                    <display:column title="Jenis Bayaran">
                        <c:if test="${line.statusTerima.kod ne null}">
                            ${line.statusTerima.nama}
                        </c:if>
                        <c:if test="${line.statusTerima.kod eq null}">
                            Bayaran Kompaun
                        </c:if>
                    </display:column> 
                            
                    <display:column title="Tarikh Kompaun">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>              
                    
                   <display:column title="Tempoh Hari">${line.tempohHari}</display:column>
                            
                    <display:column title="Pesalah">
                        Nama: ${line.isuKepada} <br>
                        <c:if test="${line.noPengenalan ne null}">
                         No.K/P: ${line.noPengenalan}
                        </c:if>
                        <c:if test="${line.noPengenalan eq null}">
                         No.K/P: Tiada Rekod
                        </c:if>
                    </display:column>

                    <display:column title="Jumlah Kompaun(RM)"> 
                       
                      Yang Dikenakan: <font color="red">${line.permohonanTuntutanKos.amaunTuntutan}</font> <br>
                      Yang Dibayar:  <c:forEach items="${actionBean.senaraiMohonTuntutBayar}" var="senarai">
                            <c:if test="${senarai.permohonanTuntutanKos.idKos eq line.permohonanTuntutanKos.idKos}">
                                <font color="blue"> ${senarai.amaun}</font>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    
                    <display:column title="Status Bayaran">
                        <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                        <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                    </display:column>    
                 
                    <display:column title="Tarikh Bayar">
                        <c:forEach items="${actionBean.senaraiMohonTuntutBayar}" var="senarai">
                            <c:if test="${senarai.permohonanTuntutanKos.idKos eq line.permohonanTuntutanKos.idKos}">
                                <fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${senarai.tarikhBayar}"/>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>
                </display:table>

        </fieldset>     
      </div>    
            
</s:form>