<%-- 
    Document   : Rekod_Bayaran
    Created on : Feb 1, 2011, 11:09:44 AM
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
        var str = elmnt.value;
        if(str > ${actionBean.jumlahPampasan}){
            elmnt.value = str.substring(0,str.length-1);
            elmnt.focus();
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

    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
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
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function popupList(idPihak,idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/rekod_bayaran?showAmbilPampasanList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.RekodBayaranBaruActionBean" id="form">
    <div  id="hakmilik_details">
        <div align="center">
            <table style="width:99.2%" bgcolor="purple">
                <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PENGAMBILAN: REKOD BAYARAN BARU</font></font>
                        </div></td></tr>
            </table>
        </div><br /><br />

        <s:messages/>
        <s:errors/>
        <%--  <s:hidden name ="hakmilik.idHakmilik"/>--%>

        <fieldset class="aras1"><br/>
            <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/rekodBayaran" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.RekodBayaranBaruActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                    </display:column>
                    <display:column title="Nilai Rampasan Borang H Awalan" ></display:column>
                    <display:column title="Borang H" >
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar" onClick="doViewReport('${actionBean.dokumenH}')" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                    </display:column>
                    <display:column title="Borang I" >
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar" onClick="doViewReport('${actionBean.dokumenI}')" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
        <br /><br />

        <c:if test="${actionBean.hakmilik ne null}">
            <fieldset class="aras1">
                <legend>Pengiraan Pampasan Baru</legend><br />
                <br />
                <br />
                <%--<c:if test="${actionBean.permohonanPihak ne null}">--%>
                <%--<c:if test="${showDetails}">     --%>     
                    <table width="100%">
                        <tr>
                            <td width="30%"><label  >Tarikh Borang H Awalan :</label></td>
                            <td>${actionBean.tarikhTerimaHAwalan}<br/></td>
                        </tr>
                        <tr>
                            <td><label >Tarikh Borang H / Borang K :</label></td>
                            <td>${actionBean.tarikhTerimaHK}<br/></td>
                        </tr>
                        <tr>
                            <td><label>Tempoh :</label></td>
                            <td><s:text name="tempoh" size="50" id="tempoh" onkeyup="validateNumber(this,this.value);"/><br /></td>
                        </tr>
                        <tr>
                            <td><label >Perbezaan Amaun Pampasan : </label></td>
                            <td><s:text name="perbezaanAmaun" size="50" id="perbezaanAmaun" value="${actionBean.amaun}" onkeyup="validateNumber(this,this.value);"/><br /></td>
                        </tr>
                        <tr>
                            <td><label >Faedah 8% Per Annum</label></td>
                            <td><s:text name="faedah" size="50" id="faedah" onkeyup="validateNumber(this,this.value);"/><br /></td>
                        </tr>
                        <tr>
                            <td><label >Jumlah Pampasan Baru</label></td>
                            <td><s:text name="jumPampasanBaru" size="50" id="jumPampasanBaru" onkeyup="validateNumber(this,this.value);"/><br /></td>
                        </tr>
                    </table>
                    <br/><br/>

                    <div align="center">
                        <s:hidden name="idPihak" />
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                            </div>
                            <br />
                            </c:if>
                        </fieldset>
                        <fieldset class="aras1">
                            <legend>Nilai Pampasan Mengikut Syer</legend><br />
                            <br />
                            <br />
                            <%--<c:if test="${actionBean.permohonanPihak ne null}">--%>
                            <p align="center">
                                <display:table class="tablecloth"  cellpadding="0" cellspacing="0" requestURI="/pengambilan/rekodBayaran" id="line2">
                                        <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                                        <display:column title="Nama T/T" >
                                            <%-- <c:if test="${line1.nama ne null}"> ${line1.nama} </c:if>
                                             <c:if test="${line1.nama eq null}"> - </c:if>--%>
                                        </display:column>
                                        <display:column title="Syer" >
                                            <%-- <c:if test="${line1.noPengenalan ne null}"> ${line1.noPengenalan} </c:if>
                                             <c:if test="${line1.noPengenalan eq null}"> - </c:if>--%>
                                        </display:column>
                                        <display:column title="Amaun" style="vertical-align:baseline">
                                            <%--  <c:if test="${line1.alamat1 ne null}">
                                                  ${line1.alamat1}
                                                  <c:if test="${line1.alamat2 ne null}"> , ${line1.alamat2} </c:if>
                                                  <c:if test="${line1.alamat3 ne null}"> , ${line1.alamat3} </c:if>
                                                  <c:if test="${line1.alamat4 ne null}"> , ${line1.alamat4} </c:if>
                                              </c:if>
                                              <c:if test="${line1.alamat1 eq null}">
                                                  -
                                              </c:if>--%>
                                        </display:column>
                                </display:table>
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah();"/>&nbsp;
                                <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;
                            </p>
                            <br/><br/>

                            <div align="center">
                                <s:hidden name="idPihak" />
                                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                    </div>
                                    <br />
                                </fieldset>
                               <%-- </c:if>--%>

                            </div>
                            </s:form>
