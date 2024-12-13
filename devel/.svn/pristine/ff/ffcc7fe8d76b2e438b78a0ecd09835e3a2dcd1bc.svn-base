<%-- 
    Document   : rekodPenerimaanDeposit
    Created on : Sep 28, 2010, 6:09:57 PM
    Author     : Rajesh
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){


        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
    });

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function popupList(idPihak,idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/rekodPenerimaanDeposit?showAkuanBayaranList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=400");
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.DepositMelakaActionBean" >
<script type="text/javascript">

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanDepositActionBean">
    <div  id="hakmilik_details">
        <div align="center">
            <table style="width:99.2%" bgcolor="purple">
                <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PENERIMAAN 50% DEPOSIT</font></font>
                        </div></td></tr>
            </table>
        </div><br /><br />

        <s:messages/>
        <s:errors/>

        <fieldset class="aras1"><br/>
            <div align="center">
                <%-- <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                requestURI="/pengambilan/rekodPenerimaanDeposit" id="line">
                     <display:column title="No" sortable="true">${line_rowNum}</display:column>
                     <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                     <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                     <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                     <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                     <display:column title="Pemohon" >
                         <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                             <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanDepositActionBean"
                                     event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                 <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                 <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                 ${senarai.pihak.nama}
                             </s:link>
                             <br/>
                         </c:forEach>
                     </display:column>
                     <display:column title="Amaun Deposit">
                         <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                             <c:set var="count" value="0"/>
                             <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                 <c:if test="${senarai.pihak.idPihak == list.perbicaraanKehadiran.pihak.pihak.idPihak && line.hakmilik.idHakmilik == list.perbicaraanKehadiran.pihak.hakmilik.idHakmilik}">
                                     <c:set value="${count + 1}" var="count"/>
                                     <a href="javascript:popupList('${senarai.pihak.idPihak}','${line.hakmilik.idHakmilik}');">
                                         ${list.jumTerimaPampasan}
                                     </a><br/>
                                 </c:if>
                             </c:forEach>

                        </c:forEach>
                    </display:column>
                </display:table>

                --%>
                <display:table class="tablecloth" name="${actionBean.pemohonList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/rekodPenerimaanDeposit" id="linet">
                    <display:column title="No" sortable="true">${linet_rowNum}</display:column>
                    <display:column  title="Nama Pemohon" >  
                        <%-- <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanDepositActionBean"
                                event="pemohonDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idPihak" value="${linet.pihak.idPihak}"/>--%> 
                        ${linet.pihak.nama} 
                        <%--</s:link>--%>
                    </display:column>
                    <display:column title="Alamat">${linet.pihak.alamat1}
                        <c:if test="${linet.pihak.alamat2 ne null}">,</c:if>
                        ${linet.pihak.alamat2}
                        <c:if test="${linet.pihak.alamat3 ne null}"> </c:if>
                        ${linet.pihak.alamat3}
                        <c:if test="${linet.pihak.alamat4 ne null}">,</c:if>
                        ${linet.pihak.alamat4}
                        <c:if test="${linet.pihak.poskod ne null}">,</c:if>
                        ${linet.pihak.poskod}
                        <c:if test="${linet.pihak.negeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${linet.pihak.negeri.nama}</font>
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No.Telefon" />

                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />${linet.pihak.noPengenalan}

                    <display:column title="Amaun 125% Deposit">
                       

                        ${$actionBean.caraBayaranList[line_rowNum-1]}


                    </display:column>
                </display:table>
            </div>
        </fieldset>
        <c:if test="${showDetails}">
            <table width="100%">
                <tr>
                    <td width="30%"><label >Jumlah deposit yang diterima (RM) :</label></td>
                    <td><s:text name="jumTerimaPampasan" size="50" onkeyup="validateNumber(this,this.value);"/><br /></td>
                </tr>
                <tr>
                    <td><%--<label >Jumlah pampasan yang diterima (RM) :</label>--%></td>
                    <td><s:hidden name="jumTerimaPampasan" onkeyup="validateNumber(this,this.value);"/><br /></td>
                </tr>
                <tr>
                    <td><label >Cara Pembayaran : </label></td>
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran1" value="CT" /> Cek Tempatan <br /></td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran2" value="CL"/> Cek Luar <br /></td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran3" value="CB"/> Cek Bank Negara <br /></td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran4" value="DB"/> Bank Draf <br /></td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran5" value="T"/> Tunai <br /></td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="XT"/> Tiada Pembayaran</td>
                </tr>

                <tr>
                    <td><label >No. :</label></td>
                    <td><s:text name="noDok" size="50"/></td>
                </tr>
                <tr>
                    <td><label >Tarikh :</label></td>
                    <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td><label >Bank :</label></td>
                    <td><s:select name="kodBank.kod" style="width:300px;" id="kodBank" >
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                    </td>
                </tr>
            </table>
            <br/><br/>

            <div align="center">
                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                <s:button name="simpanPemohon" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </div>

            <br />
        </c:if>

    </div>
</s:form>
</s:form>
