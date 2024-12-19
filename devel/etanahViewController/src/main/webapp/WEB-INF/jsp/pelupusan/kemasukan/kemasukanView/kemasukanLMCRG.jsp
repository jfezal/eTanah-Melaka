<%-- 
    Document   : kemasukanLMCRG
    Created on : Feb 21, 2013, 11:14:35 AM
    Author     : Shazwan
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

    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function doSomething(val){
          
        if(val=='GRN'||val=='GM'){
            //alert(val);
            $('#jikaPajakan').show();
        }else{
            //alert(val);
            $('#jikaPajakan').hide();
        }
    }
        
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2('main');
        self.close();
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

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.negeri eq '04'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Daerah :</td>
                                <td>${actionBean.kodDaerah}                                   
                                </td>
                            </tr>
                            <c:if test="${actionBean.forBPM}">
                                <tr>
                                    <td><font color="red" size="4">*</font>Mukim/Pekan/Bandar :</td>
                                    <td>
                                        <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb" onchange="doSomething2(this.form);">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodBPMLMCRGPJLB}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <s:hidden name="test" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}" style="width:300px;" id="_kodSeksyen2"/>
                            <c:if test="${actionBean.forSeksyen}">
                                <tr>
                                    <td>Seksyen :</td>
                                    <td>
                                        <s:select name="kodSeksyen.kod" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}"style="width:300px;" id="_kodSeksyen">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodSeksyen}" value="kod" label="nama" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td><font color="red" size="4">*</font>Tempat/Lokasi Tanah Dipohon :</td>
                                <td>
                                    <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" id="tempat" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Nombor Lot/PT :</td>
                                <td>
                                    <s:select name="kodLot.kod" value="${actionBean.hakmilikPermohonan.lot.kod}" id="kodlot">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                    </s:select>
                                    <s:text name="hakmilikPermohonan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No. Pelan Syit Piawai :</td>
                                <td>
                                    <s:text name="noLitho" size="20" id="noLitho"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Penandaan blok (unit):</td>
                                <td>
                                    <s:text name="tandaBlok" size="30" id="tandaBlok"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><center>
                                        (Suatu peta berdasarkan kepada Siri (Baru) Peta Topografi Malaysia berskala 1 : 63,360 <br>
                                        atau 1 : 50,000 menunjukkan kawasan tersebut handaklah dikepilkan)
                                    </center>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Keluasan :</td>
                                <td>
                                    <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                    <s:select name="keluasanUOM" id="uJarakKUOM">
                                        <s:option value="">Sila Pilih</s:option>
                                        <%--<s:option value="JK">Kaki</s:option>--%>
                                        <s:option value="H">Hektar</s:option>
                                        <s:option value="M">Meter Persegi</s:option>
                                    </s:select>
                                    <s:hidden name="kodUnitLuas.kod" value="H"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Logam/Mineral yang dicari :</td>
                                <td>
                                    <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Tujuan:</td>
                                <td>
                                    <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align:center;" colspan="3">      
                                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
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