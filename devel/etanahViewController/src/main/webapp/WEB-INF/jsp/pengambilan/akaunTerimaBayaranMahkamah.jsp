<%--
    Document   : AkuanTerimaBayaran
    Created on : Sep 28, 2010, 6:31:48 PM
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
        var url = '${pageContext.request.contextPath}/pengambilan/akaunTerimaBayaranMahkamah?showAkuanBayaranList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=400");
    }
    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function validation() {
        if($("#jumTerimaPampasan").val() == ""){
            alert('Sila pilih " Jumlah pampasan yang diterima (RM) : " terlebih dahulu.');
            $("#jumTerimaPampasan").focus();
            return false;
        }
        if ($("input[name='kodCaraBayaran.kod']:checked").val() != 'CT' &&
            $("input[name='kodCaraBayaran.kod']:checked").val() != 'CL' &&
            $("input[name='kodCaraBayaran.kod']:checked").val() != 'CB' &&
            $("input[name='kodCaraBayaran.kod']:checked").val() != 'DB' &&
            $("input[name='kodCaraBayaran.kod']:checked").val() != 'T' &&
            $("input[name='kodCaraBayaran.kod']:checked").val() != 'XT') {
            alert('Sila pilih " Cara Pembayaran : " terlebih dahulu.');
            $("#kodCaraBayaran1").focus();
            return false;
        }

        if($("#datepicker").val() == ""){
            alert('Sila pilih " Tarikh : " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }
        if($("#kodBank").val() == ""){
            alert('Sila pilih " Bank : " terlebih dahulu.');
            $("#kodBank").focus();
            return false;
        }
        return true;

    }
    function baca(){
       
        var a=document.getElementById("pampasan").value;
        
        var b=document.getElementById("jumTerimaPampasan");
        b.value=a;
        // $("#jumTerimaPampasan").val()==$("#pampasan").val()
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" beanclass="etanah.view.stripes.pengambilan.AkaunTerimaBayaranMahkamahActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <c:set value="" var="jumPampas"/>
        <c:set value="" var="nama"/>
        <fieldset class="aras1"><br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/akuanTerimaBayaran" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                    <%--<display:column property="hakmilik.noLot" title="No Lot/No PT" />--%>
                    <display:column title="No Lot/No PT" >${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tuan Tanah" >
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" varStatus="loop" var="senarai">
                            <c:if test="${senarai.aktif eq 'Y'}">
                                ${loop.index+1}. <s:link beanclass="etanah.view.stripes.pengambilan.AkaunTerimaBayaranMahkamahActionBean"
                                                         event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                    <s:param name="nama" value="${senarai.pihak.nama}"/>
                                    <s:param name="nombor" value="${loop.index+1}"/>
                                    ${senarai.pihak.nama}
                                    <c:set value="${senarai.pihak.nama}" var="nama"/>
                                </s:link>
                                <br/>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    <display:column title="Amaun Bayaran">
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="lp">
                            <c:if test="${senarai.aktif eq 'Y'}">
                                <c:set var="count" value="0"/>
                                <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                    <c:if test="${senarai.pihak.idPihak == list.perbicaraanKehadiran.pihak.pihak.idPihak && line.hakmilik.idHakmilik == list.perbicaraanKehadiran.pihak.hakmilik.idHakmilik}">
                                        <c:set value="${count + 1}" var="count"/>
                                        ${lp.index+1}. <a href="javascript:popupList('${senarai.pihak.idPihak}','${line.hakmilik.idHakmilik}');">
                                            ${list.jumTerimaPampasan}
                                        </a><br/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </display:column>
                </display:table>

                <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="lm">                   

                    <c:set value="${lm.pihak.syerPembilang}" var="a"/>
                    <c:set value="${lm.pihak.syerPenyebut}" var="b"/>
                    <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                    <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                    <c:set value="${d*(a/b)}" var="e"/>
                    <c:set value="${e}" var="f"/>
                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="listx">
                        <c:if test="${lm.pihak.pihak.idPihak == listx.pihak.idPihak}">

                            <c:set var="B" value="0"/>

                            <c:forEach items="${lm.senaraiPenilaian}" var="list1">

                                <c:set value="${B + list1.amaun}" var="B"/>
                            </c:forEach>
                            <c:set value="${B}" var="g"/>
                            <c:if test="${listx.pihak.idPihak==actionBean.idPihak}">
                                <c:set value="${f+g}" var="jumPampas"/>
                            </c:if>
                        </c:if>
                    </c:forEach>


                </c:forEach> 
                Jumlah Pampasan bagi ${actionBean.nama} : <fmt:formatNumber pattern="#,##0.00" value="${jumPampas}"/>
            </div>
        </fieldset>
        <c:if test="${showDetails}">

            <table width="100%">
                <tr>
                    <td width="30%"><label >Urusan/Tujuan Permohonan :</label></td>
                    <td>${actionBean.permohonan.sebab}<br /></td>
                </tr>
                <tr>
                    <s:hidden name="pampasan" id="pampasan" value="${jumPampas}" formatPattern="#########.00" ></s:hidden>
                    <td width="30%"><label >Amaun (RM) :</label></td>
                    <td><s:text name="jumTerimaPampasan" id="jumTerimaPampasan" size="50" value="${jumPampas}" formatPattern="###,###,###.00" onclick="baca()" onkeyup="validateNumber(this,this.value);"/><br /></td>
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
                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="EF"/> EFT / Wang Dalam Pindahan<br /></td>
                </tr>
                <%--              <tr>
                                  <td><label >&nbsp;&nbsp;</label></td>
                                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="XT"/> Tiada Pembayaran</td>
                              </tr>--%>
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
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:hidden name="nombor" value="${actionBean.nombor}"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <%--onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"--%>
            </div>

            <br />
        </c:if>

    </div>
</s:form>
