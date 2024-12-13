<%-- 
    Document   : rekodBayaranTuntutanToPemohon
    Created on : 06-June-2011, 11:36:32
    Author     : massita
--%>

<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
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
    function popupList(idPihak,idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/rekodBayaranTuntutanToPemohon?showAkuanBayaranList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=400");
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

    function HideforTunai() {
        $("#nodok").attr("disabled",true);
        $("#nodok").val("");
        $("#tarikhDok").attr("disabled",false);
        $("#tarikhDok").val("");
        $("#kodBank").attr("disabled",true);
        $("#kodBank").val("");
        //               $('#nodok').hide();
        //               $('#kodBank').hide();
        //               $('#no').hide();
        //               $('#bank').hide();
    }
    function Show(){
        $("#nodok").attr("disabled",false);
        $("#tarikhDok").attr("disabled",false);
        $("#kodBank").attr("disabled",false);
        //                $('#nodok').show();
        //                $('#datepicker').show();
        //                $('#kodBank').show();
        //                $('#no').show();
        //                $('#tarikh').show();
        //                $('#bank').show();
    }

    function validation() {
        if($("#amaunTuntutan").val() == ""){
            alert('Sila pilih " Amaun Diterima (RM) : " terlebih dahulu.');
            $("#amaunTuntutan").focus();
            return false;
        }
        return true;
    }
        
    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>
<s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.RekodBayaranTuntutanToPemohonActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <fieldset class="aras1"><br/>
            <c:if test="${showCek}">
                <c:if test="${actionBean.hakmilik ne null && actionBean.permohonanPihak ne null}" >
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>Pemohon</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>No Hakmilik</th>
                            </tr>
                            <tr>
                                <td>
                                    ${actionBean.permohonan.senaraiPemohon[0].pihak.nama}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.lot.nama}${actionBean.hakmilik.noLot}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.daerah.nama}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.bandarPekanMukim.nama}
                                </td>
                                <td>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.RekodBayaranTuntutanToPemohonActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${actionBean.permohonanPihak.pihak.idPihak}"/>
                                        <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                                        <s:param name="display" value="true"/>
                                        ${actionBean.hakmilik.idHakmilik}
                                    </s:link>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
            </fieldset>
            <br/><br/>
            <c:if test="${showDetails}">
                <div  class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Pembayaran</legend><br />
                        <div  align="center">
                            <table width="100%">
                                <tr>
                                    <td><label for="nama">Nama Tuan Tanah(Pembayar):</label></td>
                                    <td>${actionBean.permohonanPihak.pihak.nama}</td>
                                </tr>
                                <tr>
                                    <td width="30%"><label >Amaun Diterima  (RM) :</label></td>
                                    <%--<s:text name="amaunTuntutan" id="amaunTuntutan" size="30" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/>--%>
                                    <td><s:text name="sum" id="sum" size="30" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/><br /></td>
                                </tr>
                                <tr>
                                    <td><label >Cara Pembayaran : </label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran1" value="CT" /> Cek Tempatan <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran2" value="CL" onclick="Show();"/> Cek Luar <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran3" value="CB" onclick="Show();"/> Cek Bank Negara <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran4" value="DB" onclick="Show();"/> Bank Draf <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran5" value="T" onclick="HideforTunai();"/> Tunai <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="XT" onclick="Show();"/> Tiada Pembayaran</td>
                                </tr>
                                <tr>
                                    <td><label id="no" >No. :</label></td>
                                    <td><s:text name="noDok" size="50" id="nodok"/></td>
                                </tr>
                                <tr>
                                    <td><label id="tarikh" >Tarikh :</label></td>
                                    <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
                                </tr>
                                <tr>
                                    <td><label id="bank" >Bank :</label></td>
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
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                            </div>

                            <br/>

                        </div>
                    </fieldset>
                </div>
            </c:if>
        </c:if>

        <fieldset class="aras1"><br/>
            <c:if test="${showEFT}">
                <c:if test="${actionBean.hakmilik ne null && actionBean.permohonanPihak ne null}" >
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>Pemohon</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>No Hakmilik</th>
                            </tr>
                            <tr>
                                <td>
                                    ${actionBean.permohonan.senaraiPemohon[0].pihak.nama}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.lot.nama}${actionBean.hakmilik.noLot}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.daerah.nama}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.bandarPekanMukim.nama}
                                </td>
                                <td>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.RekodBayaranTuntutanToPemohonActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${actionBean.permohonanPihak.pihak.idPihak}"/>
                                        <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                                        <s:param name="display" value="false"/>
                                        ${actionBean.hakmilik.idHakmilik}
                                    </s:link>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
            </fieldset>
            <br/><br/>
            <c:if test="${showDetails}">
                <div  class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Pembayaran</legend><br />
                        <div  align="center">
                            <table width="100%">
                                <tr>
                                    <td><label for="nama">Nama Tuan Tanah(Pembayar):</label></td>
                                    <td>${actionBean.permohonanPihak.pihak.nama}</td>
                                </tr>
                                <tr>
                                    <td width="30%"><label >Amaun Diterima  (RM) :</label></td>
                                    <%--<s:text name="amaunTuntutan" id="amaunTuntutan" size="30" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/>--%>
                                    <td><s:text name="sum" id="sum" size="30" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/><br /></td>
                                </tr>
                                <tr>
                                    <td><label id="no" >No. Akaun :</label></td>
                                    <td><s:text name="noDok" size="50" id="noDok"/></td>
                                </tr>
                                <%--<tr>
                                    <td><label id="tarikh" >Tarikh :</label></td>
                                    <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
                                </tr>--%>
                            </table>
                            <br/><br/>

                            <div align="center">
                                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                <s:button name="simpanEFT" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                            </div>

                            <br/>

                        </div>
                    </fieldset>
                </div>
            </c:if>
            </c:if>
        </div>
</s:form>


