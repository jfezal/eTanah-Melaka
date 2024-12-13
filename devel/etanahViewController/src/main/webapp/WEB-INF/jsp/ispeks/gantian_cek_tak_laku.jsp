<%-- 
    Document   : gantian_cek
    Created on : Jul 7, 2015, 12:12:16 PM
    Author     : haqqiem
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="etanah.model.Pengguna"%>
<html>
    <head><title>Carian Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;

                $.get(url,q,
                function(data){
                    if(data == '0'){
                        alert("Tiada Hakmilik Dijumpai.Sila pastikan data - data hakmilik adalah tepat");
                    }else if(data == '1'){
                        alert("Sila Pilih Kod Bandar Pekan Mukim");
                    }else if(data == '2'){
                        alert("Sila Pilih Daerah");
                    }else if(data == '3'){
                        alert("Sila Pilih Jenis Lot");
                    }else if(data == '4'){
                        alert("Sila Masukkan No Lot / No PT");
                    }
                    else{
                        opener.location.reload();
                        self.close();;
                    }
           
                },'html');
            }
           
            $(document).ready( function(){
           $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

                    });
        </script>
    </head>

<div align="center">
    <table style="width:100%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Utiliti Gantian Cek Tak Laku</font>
                </div>
            </td>
        </tr>
    </table>
</div>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.ispeks.IspeksGantianCekTakLakuActionBean" id="gantian" name="form">
    <s:messages/>
    <s:errors/>
    <s:text name="caraBayar.idCaraBayaran" value="${actionBean.caraBayar.idCaraBayaran}" style="display:none"/>
    <div class="subtitle">
    </div><br>
    <div class="subtitle" id="details">
        <fieldset class="aras1">
            <legend>Maklumat Resit</legend>
            <div class="instr-fieldset">
                <font color="red">Perhatian :</font><br>
                Sila tekan butang <img title="Sila Klik Untuk Gantian Cek" src='${pageContext.request.contextPath}/pub/images/edit.gif' /> untuk menggantikan cek baharu.<br>
                Medan bertanda <em>*</em> adalah mandatori.
            </div>
            <p>
                <label>Cara Bayaran :</label>
                ${actionBean.caraBayar.kodCaraBayaran.nama} &nbsp;
            </p>
            <p>
                <label>Cara Bayaran :</label>
                ${actionBean.caraBayar.bank.nama} &nbsp;
            </p>
            <p>
                <label>Cawangan:</label>
                ${actionBean.caraBayar.bankCawangan} &nbsp;
            </p>
            <p>
                <label>Amaun Bayaran (RM)  :</label>
                <fmt:formatNumber value="${actionBean.caraBayar.amaun}" pattern="#,###,###.00"/> &nbsp;
            </p>
            <p>
                <label>Dibayar Oleh :</label>
                ${actionBean.dokumenKewangan.isuKepada} &nbsp;
            </p>

            <p>
                <label>No. Rujukan Cek :</label>
                ${actionBean.caraBayar.noRujukan} 
                <a id="" onclick="editCheque(this.form);" onmouseover="this.style.cursor = 'pointer';">
                    <img title="Sila Klik Untuk Gantian Cek" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                </a>&nbsp;
            </p>

            <p>
                <label>Tarikh Cek :</label>
                <fmt:formatDate value="${actionBean.caraBayar.tarikhCek}" pattern="dd/MM/yyyy hh:mm aa"/>
            </p>

            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiResit}" 
                               requestURI="/hasil/gantian_resit" id="line">
                    <display:column title="Bil" sortable="true" > <div align="center">${line_rowNum}</div></display:column>
                    <display:column title="Nombor Resit Terlibat" property="idDokumenKewangan"/>$

                    <display:column title="Transaksi" >
                        <c:forEach items="${line.senaraiTransaksi}" var="senarai" varStatus="a">
                            <c:out value="${senarai.kodTransaksi.kod}" /> - <c:out value="${senarai.kodTransaksi.nama}" /> 
                            <c:if test="${senarai.kodTransaksi.kod eq '61401' 
                                          or senarai.kodTransaksi.kod eq '61402' 
                                          or senarai.kodTransaksi.kod eq '76152' }">
                                (<c:out value="${senarai.untukTahun}" />)
                            </c:if>
                            <br>
                        </c:forEach>
                    </display:column>

                    <display:column title="Nombor Akaun" >
                        <c:if test="${line.akaun.noAkaun ne null}">
                            <c:out value="${line.akaun.noAkaun}"/>
                        </c:if>
                        <c:if test="${line.akaun.noAkaun eq null}">
                            -
                        </c:if>
                    </display:column>

                    <display:column title="Tarikh Masuk">
                        <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                    </display:column>

                    <display:column title="Dimasuk Oleh" >
                        <c:out value="${line.infoAudit.dimasukOleh.nama}"/>
                    </display:column>

                </display:table>
            </div>
        </fieldset>
    </div>

    <div class="subtitle" id="new">
        <fieldset class="aras1">
            <legend>Maklumat Resit Baru</legend>
            <p>
                <label><em>*</em>Cara Bayaran :</label>
                <s:select name="kodCaraBayar" id="cb">
                    <s:option label="Pilih ..."  value="0" />
                    <c:forEach items="${actionBean.senaraiKodCaraBayaran}" var="i" >
                        <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            
            <p>
                <label><em>*</em>Bank :</label>
                <s:select name="bank" id="bank">
                    <s:option label="Pilih ..."  value="0" />
                    <c:forEach items="${actionBean.senaraiKodBank}" var="i" >
                        <s:option value="${i.kod}">${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>

            <p>
                <label><em>*</em>Cawangan  :</label>
                <s:text name="cawangan" id="caw" size="28" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><em>*</em>No.Rujukan  :</label>
                <s:text name="noRujukan" id="ruj" size="28"/>
            </p>

            <p>
                <label><em>*</em>Tarikh :</label>
                <s:text name="tarikhCek" id="trh" size="28" readonly="true" maxlength="10" class="datepicker"/>
            </p>
            
            <p>
                <label>Amaun (RM)  :</label>
                <s:text name="amaun" id="amaun" size="28" readonly="true" style="text-align:right;"/>
            </p>
            
            <p>
                <label>&nbsp;</label>
                <s:submit name="Step3" class='btn' value="Simpan" id='search' onclick="return save(this.form)"/>
                <s:button name="" class='btn' value="Isi Semula" onclick="clearText('gantian');"/>&nbsp;
            </p><br>
        </fieldset>
    </div>

    <table style="width:100%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"> </font>
                </div>
            </td>
        </tr>
    </table>
</s:form>