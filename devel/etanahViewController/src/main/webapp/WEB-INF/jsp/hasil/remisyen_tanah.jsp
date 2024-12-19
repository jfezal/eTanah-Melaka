<%-- 
    Document   : remisyen_tanah
    Created on : Mar 1, 2011, 10:28:26 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function popupIdKumpulan(){
        var f = document.rt1;
        var url = f.action + '?popupSearchIdKumpulan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbar=1");
    }

    function refreshIdKumpulan(id){
        <%--var idkumpulan = $(".idKumpulan").val();--%>
        var idkumpulan = id;
        var idhakmilik = $(".idHakmilik").val();
        var noakaun = $(".noAkaun").val();
        var q = $('#form1').serialize();
        var url = document.rt1.action + '?refreshIdKumpulan&idKumpulan='+idkumpulan+'&idHakmilik='+idhakmilik+'&noAkaun='+noakaun;// + event;
        <%--var url = document.rt1.action + '?refreshIdKumpulan&idHakmilik='+idhakmilik+'&noAkaun='+noakaun;// + event;--%>
        window.location = url+q;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function filterRemisyen(id){
        if(id=='rct'){
            $("#p_rct").show();
            $("#p_rtct").hide();
            $("#p_rdl").hide();
            $("#p_rtdl").hide();
            $("#peratusRemisyenTanah").val("");
            $("#peratusRemisyenTunggak").val("");
            $("#peratusRemisyenDenda").val("");
            $("#peratusRemisyenTunggakDenda").val("");
        }
        if(id=='rtct'){
            $("#p_rct").hide();
            $("#p_rtct").show();
            $("#p_rdl").hide();
            $("#p_rtdl").hide();
            $("#peratusRemisyenTanah").val("");
            $("#peratusRemisyenTunggak").val("");
            $("#peratusRemisyenDenda").val("");
            $("#peratusRemisyenTunggakDenda").val("");
        }
        if(id=='rdl'){
            $("#p_rct").hide();
            $("#p_rtct").hide();
            $("#p_rdl").show();
            $("#p_rtdl").hide();
            $("#peratusRemisyenTanah").val("");
            $("#peratusRemisyenTunggak").val("");
            $("#peratusRemisyenDenda").val("");
            $("#peratusRemisyenTunggakDenda").val("");
        }
        if(id=='rtdl'){
            $("#p_rct").hide();
            $("#p_rtct").hide();
            $("#p_rdl").hide();
            $("#p_rtdl").show();
            $("#peratusRemisyenTanah").val("");
            $("#peratusRemisyenTunggak").val("");
            $("#peratusRemisyenDenda").val("");
            $("#peratusRemisyenTunggakDenda").val("");
        }
    }
</script>
<script language="javascript" >
    $(document).ready(function() {
         $("#p_rtct").hide();
         $("#p_rdl").hide();
         $("#p_rtdl").hide();
    });
</script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:10px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:10em;
    }
</style>

<s:form name="rt1" beanclass="etanah.view.stripes.hasil.RemisyenTanahActionBean" id="remisyen_tanah">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>Nama Kumpulan :</label>
                <s:text name="namaKumpulan" value="" size="27" maxlength="12" id="idKumpulan" class="idKumpulan" disabled="true" onchange="this.value = this.value.toUpperCase();"/>
                <img alt='Klik Untuk Cari ID Kumpulan' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem'
                     id='carianKumpulan' onclick='popupIdKumpulan();return false;' onmouseover="this.style.cursor='pointer';" title="Carian Kumpulan.">
                <s:hidden name="idKumpulan"/>
            </p>
            <p align="right">
                <label><em>atau</em></label>
                &nbsp;
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="idHakmilik" value="" size="30" maxlength="28" id="idHakmilik" class="idHakmilik" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <c:if test="${actionBean.negeri eq 'melaka'}">
                <p>
                    <label>No. Akaun :</label>
                    <s:text name="noAkaun" value="" size="30" maxlength="28" id="noAkaun" class="noAkaun" onchange="this.value = this.value.toUpperCase();"/>
               </p>
            </c:if>
           <p>
               <label>&nbsp;</label>
               &nbsp;
           </p>
           <p align="center">
               <s:submit name="search" value="Cari" class="btn"/>
               <s:button name="reset" value="Isi Semula" class="btn" onclick="clearText('remisyen_tanah');"/>
           </p>
        </fieldset>
    </div>
    <c:if test="${actionBean.show}">
    <div class="content">
       <fieldset class="aras1">
           <legend>Maklumat Hakmilik</legend>
           <c:if test="${actionBean.showMsg}">
               <p><label><em>PEMBERITAHUAN : </em></label>
                   <c:out value="${actionBean.msg}"/>
               </p>
           </c:if>  
               <center>
                <display:table name="${actionBean.senaraiAkaun}" class="tablecloth" id="row" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/remisyenTanah">
                    <display:column title="Bil.">${row_rowNum}</display:column>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <display:column title="No. Akaun" property="noAkaun"/>
                    </c:if>
                    <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                    <c:set var="tanah" value="0"/>
                    <c:set var="tunggak" value="0"/>
                    <c:set var="denda" value="0"/>
                    <c:set var="tunggak_denda" value="0"/>
                    <c:set var="n6a_ns" value="0"/>
                    <c:set var="n6a_ml" value="0"/>
                    <!-- for transaksi batal-->
                    <c:set var="tanah_b" value="0"/>
                    <c:set var="tunggak_b" value="0"/>
                    <c:set var="denda_b" value="0"/>
                    <c:set var="tunggak_denda_b" value="0"/>
                    <c:set var="n6a_ns_b" value="0"/>
                    <c:set var="n6a_ml_b" value="0"/>
                    <c:set var="tahunSemasa" value="${actionBean.tahunSkrg}"/>
                    <c:forEach var="trans" items="${row.semuaTransaksi}">
                        <c:if test="${trans.kodTransaksi.kod eq '61401' and trans.status.kod eq 'A'}"><c:set var="tanah" value="${tanah + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '61402' and trans.status.kod eq 'A'}"><c:set var="tunggak" value="${tunggak + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'A' and trans.untukTahun eq tahunSemasa}"><c:set var="denda" value="${denda + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'A' and trans.untukTahun ne tahunSemasa}"><c:set var="tunggak_denda" value="${tunggak_denda + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '99011' and trans.status.kod eq 'A'}"><c:set var="n6a_ns" value="${n6a_ns + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '72457' and trans.status.kod eq 'A'}"><c:set var="n6a_ml" value="${n6a_ml + trans.amaun}"/></c:if>
                        <!-- for transaksi batal-->
                        <c:if test="${trans.kodTransaksi.kod eq '61401' and trans.status.kod eq 'B'}"><c:set var="tanah_b" value="${tanah_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '61402' and trans.status.kod eq 'B'}"><c:set var="tunggak_b" value="${tunggak_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'B' and trans.untukTahun eq tahunSemasa}"><c:set var="denda_b" value="${denda_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '76152' and trans.status.kod eq 'B' and trans.untukTahun ne tahunSemasa}"><c:set var="tunggak_denda_b" value="${tunggak_denda_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '99011' and trans.status.kod eq 'B'}"><c:set var="n6a_ns_b" value="${n6a_ns_b + trans.amaun}"/></c:if>
                        <c:if test="${trans.kodTransaksi.kod eq '72457' and trans.status.kod eq 'B'}"><c:set var="n6a_ml_b" value="${n6a_ml_b + trans.amaun}"/></c:if>
                    </c:forEach>
                    <%--Cukai Semasa : RM<fmt:formatNumber value="${tanah}" pattern="#,##0.00"/><br>
                    Tunggakan Cukai : RM<fmt:formatNumber value="${tunggak}" pattern="#,##0.00"/><br>
                    Denda Lewat : RM<fmt:formatNumber value="${denda}" pattern="#,##0.00"/>--%>
                    <display:column title="Cukai Tanah" style="text-align:right">
                        <%--<fmt:formatNumber value="${row.baki}" pattern="#,##0.00"/>--%>
                        RM<fmt:formatNumber value="${tanah - tanah_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Tunggakan Cukai Tanah" style="text-align:right">
                        RM<fmt:formatNumber value="${tunggak - tunggak_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${denda - denda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Tunggakan Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${tunggak_denda - tunggak_denda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <c:if test="${actionBean.negeri eq 'sembilan'}">
                        <display:column title="Notis 6A" style="text-align:right">
                            RM<fmt:formatNumber value="${n6a_ns - n6a_ns_b}" pattern="#,##0.00"/>
                        </display:column>
                    </c:if>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <display:column title="Notis 6A" style="text-align:right">
                            RM<fmt:formatNumber value="${n6a_ml - n6a_ml_b}" pattern="#,##0.00"/>
                        </display:column>
                    </c:if>
                    <c:set value="0" var="minTahun"/>
                    <%--<c:set value="0" var="nilaiRemisyen"/>--%>
                    <c:set var="rTanah" value="0"/>
                    <c:set var="rTunggak" value="0"/>
                    <c:set var="rDenda" value="0"/>
                    <c:set var="rTunggakDenda" value="0"/>
                    <!-- for transaksi batal-->
                    <c:set var="rTanah_b" value="0"/>
                    <c:set var="rTunggak_b" value="0"/>
                    <c:set var="rDenda_b" value="0"/>
                    <c:set var="rTunggakDenda_b" value="0"/>
                    <c:forEach items="${row.semuaTransaksi}" var="transaksi">
                        <c:if test="${(transaksi.kodTransaksi.kod eq '99030' or transaksi.kodTransaksi.kod eq '99051' or transaksi.kodTransaksi.kod eq '99050' or transaksi.kodTransaksi.kod eq '99052') and transaksi.untukTahun eq actionBean.tahunSkrg}">
                            <%--<c:set value="${nilaiRemisyen + transaksi.amaun}" var="nilaiRemisyen"/>--%>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99030' and transaksi.status.kod eq 'A'}"><c:set var="rTanah" value="${rTanah + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99051' and transaksi.status.kod eq 'A'}"><c:set var="rTunggak" value="${rTunggak + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99050' and transaksi.status.kod eq 'A'}"><c:set var="rDenda" value="${rDenda + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99052' and transaksi.status.kod eq 'A'}"><c:set var="rTunggakDenda" value="${rTunggakDenda + transaksi.amaun}"/></c:if>
                            <!-- for transaksi batal-->
                            <c:if test="${transaksi.kodTransaksi.kod eq '99030' and transaksi.status.kod eq 'B'}"><c:set var="rTanah_b" value="${rTanah_b + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99051' and transaksi.status.kod eq 'B'}"><c:set var="rTunggak_b" value="${rTunggak_b + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99050' and transaksi.status.kod eq 'B'}"><c:set var="rDenda_b" value="${rDenda_b + transaksi.amaun}"/></c:if>
                            <c:if test="${transaksi.kodTransaksi.kod eq '99052' and transaksi.status.kod eq 'B'}"><c:set var="rTunggakDenda_b" value="${rTunggakDenda_b + transaksi.amaun}"/></c:if>
                            <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                        </c:if>
                    </c:forEach>
                    <%--Cukai Semasa : RM<fmt:formatNumber value="${rTanah}" pattern="#,##0.00"/><br>
                    Tunggakan Cukai : RM<fmt:formatNumber value="${rTunggak}" pattern="#,##0.00"/><br>
                    Denda Lewat : RM<fmt:formatNumber value="${rDenda}" pattern="#,##0.00"/>--%>
                    <display:column title="Remisyen Cukai Tanah" style="text-align:right">
                        <%--<fmt:formatNumber value="${nilaiRemisyen}" pattern="#,##0.00"/>--%>
                        RM<fmt:formatNumber value="${rTanah - rTanah_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Remisyen Tunggakan Cukai Tanah" style="text-align:right">
                        RM<fmt:formatNumber value="${rTunggak - rTunggak_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Remisyen Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${rDenda - rDenda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Remisyen Tunggakan Denda Lewat" style="text-align:right">
                        RM<fmt:formatNumber value="${rTunggakDenda - rTunggakDenda_b}" pattern="#,##0.00"/>
                    </display:column>
                    <display:column title="Jumlah Perlu Dibayar" style="text-align:right">
                        RM<fmt:formatNumber value="${row.baki}" pattern="#,##0.00"/>
                    </display:column>
                    <c:set value="0" var="bilTahun"/>
                    <c:forEach items="${row.senaraiTransaksiKreditHadapan}" var="transaksiHdpn">
                            <%--<c:if test="${bilTahun > 0}">--%>
                                <c:set value="${transaksiHdpn.untukTahun}" var="maxTahun"/>
                            <%--</c:if>--%>
                            <c:set value="${bilTahun + 1}" var="bilTahun"/>
                    </c:forEach>
                    <display:column title="Tahun Remisyen" style="text-align:center">
                        <c:out value="${minTahun}"/><c:if test="${bilTahun >= 1}"> - <c:out value="${maxTahun}"/></c:if>
                    </display:column>
                </display:table>
               </center>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.showJana}">
    <div class="subtitle">
       <fieldset class="aras1">
           <legend>Maklumat Remisyen Tanah</legend>
            <p class=instr align="left">
                *<em>Peringatan :</em> Tempoh Tahun dikira daripada tahun sekarang iaitu tahun ${actionBean.tahunSkrg}.
            </p>
            <br>
            <%--<p>
                <label><em>*</em>Peratusan Remisyen :</label>
                <em>(Sila isi maklumat yg berkenaan dibawah.)</em>
            </p>--%>
            <p>
                <label><em>*</em>Sila Pilih Kategori Remisyen :</label>
                <s:select name="" id="katgRemisyen" onchange="filterRemisyen(this.value)">
                    <s:option label="Remisyen Cukai Tanah" value="rct" />
                    <s:option label="Remisyen Tunggakan Cukai Tanah" value="rtct" />
                    <s:option label="Remisyen Denda Lewat" value="rdl" />
                    <s:option label="Remisyen Tunggakan Denda Lewat" value="rtdl" />
                </s:select>
            </p>
            <p id="p_rct">
                <label><em>*</em>Cukai Tanah</label>
                <s:text name="peratusRemisyenTanah" value="" size="10" maxlength="3" id="peratusRemisyenTanah"
                        class="peratusRemisyenTanah" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %
            </p>
            <p id="p_rtct">
                <label><em>*</em>Tunggakan Cukai Tanah</label>
                <s:text name="peratusRemisyenTunggak" value="" size="10" maxlength="3" id="peratusRemisyenTunggak"
                        class="peratusRemisyenTunggak" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %
            </p>
            <p id="p_rdl">
                <label><em>*</em>Denda Lewat</label>
                <s:text name="peratusRemisyenDenda" value="" size="10" maxlength="3" id="peratusRemisyenDenda"
                        class="peratusRemisyenDenda" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %
            </p>
            <p id="p_rtdl">
                <label><em>*</em>Tunggakan Denda Lewat</label>
                <s:text name="peratusRemisyenTunggakDenda" value="" size="10" maxlength="3" id="peratusRemisyenTunggakDenda"
                        class="peratusRemisyenTunggakDenda" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %
            </p>
            <%--<p>
                <label>&nbsp;</label>
                <table border="0">
                    <tr>
                        <td id="tdLabel">Cukai Tanah :</td>
                        <td id="tdDisplay"><s:text name="peratusRemisyenTanah" value="" size="10" maxlength="3" id="peratusRemisyenTanah" class="peratusRemisyenTanah" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %</td>
                    </tr>
                    <tr>
                        <td id="tdLabel">Tunggakan Cukai Tanah :</td>
                        <td id="tdDisplay"><s:text name="peratusRemisyenTunggak" value="" size="10" maxlength="3" id="peratusRemisyenTunggak" class="peratusRemisyenTunggak" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %</td>
                    </tr>
                    <tr>
                        <td id="tdLabel">Denda Lewat :</td>
                        <td id="tdDisplay"><s:text name="peratusRemisyenDenda" value="" size="10" maxlength="3" id="peratusRemisyenDenda" class="peratusRemisyenDenda" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %</td>
                    </tr>
                    <tr>
                        <td id="tdLabel">Tunggakan Denda Lewat :</td>
                        <td id="tdDisplay"><s:text name="peratusRemisyenTunggakDenda" value="" size="10" maxlength="3" id="peratusRemisyenTunggakDenda" class="peratusRemisyenTunggakDenda" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %</td>
                    </tr>
                </table>
            </p>--%>
            <br>
            <%--<p>
                <label><em>*</em>Peratusan Remisyen :</label>
                <s:text name="peratusRemisyen" value="" size="10" maxlength="3" id="peratusRemisyen" class="peratusRemisyen" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> %
            </p>--%>
            <p>
                <label><em>*</em>Tempoh Tahun :</label>
                <s:text name="tahunRemisyen" value="" size="10" maxlength="3" id="tahunRemisyen" class="tahunRemisyen" onkeyup="validateNumber(this,this.value);" onchange="this.value = this.value.toUpperCase();"/> Tahun
            </p>
            <br>
            <p align="center">
                <s:submit class="btn" name="simpanRemisyen" value="Jana"/>&nbsp;
            </p>
            </fieldset>
        </div>
    </c:if>
</s:form>
