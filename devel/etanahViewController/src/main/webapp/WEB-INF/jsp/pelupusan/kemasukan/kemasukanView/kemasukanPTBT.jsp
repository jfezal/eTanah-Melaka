<%-- 
    Document   : kemasukanPBMT
    Created on : Mar 14, 2013, 5:23:11 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEMASUKAN MAKLUMAT TANAH</title>
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});     
    }); //END OF READY FUNCTION

   

    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
    }
        
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
    
      
</script>
<body>
    <script type="text/javascript">
        // Allow the user to be warned by default.
        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

        function refreshpage()
        {
            NoPrompt();
        <%--alert('aa');--%>
        <%--doUnBlockUI();--%>
                opener.refreshV2MaklumatTanah();
                self.close();

            }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.negeri eq '04'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Daerah :</td>
                                <td>
                                    ${actionBean.permohonan.cawangan.daerah.nama}
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Mukim/Pekan/Bandar :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Seksyen :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Tempat/Lokasi Tanah Dipohon :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lokasi}
                                </td>
                            </tr>
                            <tr>
                                <td>Nombor Lot/PT :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lot.nama} &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.noLot}
                                </td>
                            </tr>
                            <tr>
                                <td>Luas Dipohon :</td>
                                <td>
                                    <s:format value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodUnitLuas.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Kategori Tanah :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                            </tr>
                            <tr>
                                <td>Kegunaan Tanah :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodKegunaanTanah.kod}</td>
                            </tr>
                            <tr>
                                <td>Jarak :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarak}&nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.unitJarak.kod}&nbsp;
                                    <font color="#003194"><b>dari</b></font>&nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDari}
                                </td>
                            </tr>
                            <tr>
                                <td>Nama Jalan/Keretapi/Pantai/Pekan/Sungai :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariNama}</td>
                            </tr>
                            <tr>
                                <td>Jarak Dari Kediaman :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediaman} &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediamanUom.kod}</td>
                            </tr>
                            <tr>
                                <td style="text-align:center;" colspan="3">      
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="tutupPapar()"/>
                                </td>
                            </tr>  
                        </table>       
                        <br/>
                    </c:when>
                    <c:when test="${actionBean.negeri eq '05'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Daerah :</td>
                                <td>
                                    ${actionBean.permohonan.cawangan.daerah.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Mukim/Pekan/Bandar :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Seksyen :</td>
                                <c:if test="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.nama eq null}">
                                    <td>
                                        -
                                    </td>
                                </c:if>
                                <c:if test="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.nama ne null}">
                                    <td>
                                        ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodSeksyen.nama}
                                    </td>
                                </c:if>
                            </tr>
                            <tr>
                                <td>Tempat/Lokasi Tanah Dipohon :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lokasi}
                                </td>
                            </tr>
                            <tr>
                                <td>Nombor Lot/PT :</td>
                                <td>
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lot.nama} &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.noLot}
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                <tr>
                                    <td>Pembahagian Tanah :</td>
                                    <td>

                                        <c:if test="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.catatan eq 'SL'}">
                                            Keseluruhan Tanah
                                        </c:if>
                                        <c:if test="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.catatan eq 'BL'}">
                                            Sebahagian Tanah
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>Isi Padu Dipohon :</td>
                                <td>
                                    <s:format value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodUnitLuas.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Jarak Kedalaman :</td>
                                <td>
                                    <s:format value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kedalamanTanah}" formatPattern="#,###,##0.0000"/> &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kedalamanTanahUOM.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>Kategori Tanah :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                            </tr>
                            <tr>
                                <td>Kegunaan Tanah :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodKegunaanTanah.nama}</td>
                            </tr>
                            <tr>
                                <td>Jarak :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarak}&nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.unitJarak.nama}&nbsp;
                                    <font color="#003194"><b>dari</b></font>&nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDari}
                                </td>
                            </tr>
                            <tr>
                                <td>Nama Jalan/Keretapi/Pantai/Pekan/Sungai :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariNama}</td>
                            </tr>
                            <tr>
                                <td>Jarak Dari Kediaman :</td>
                                <td>${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediaman} &nbsp;
                                    ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.jarakDariKediamanUom.nama}</td>
                            </tr>
                            <tr>
                                <td style="text-align:center;" colspan="3">      
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                </td>
                            </tr>  
                        </table>       
                        <br/>
                    </c:when>    
                </c:choose>
            </fieldset>
        </div>
    </s:form>
</body>
