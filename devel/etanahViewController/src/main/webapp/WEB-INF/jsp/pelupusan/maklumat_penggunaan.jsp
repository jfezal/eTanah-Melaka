<%--
    Document   : maklumat_penggunaan.jsp ( FOR URUSAN BORANG KHAS )
    Created on : September 6, 2011, 10:31:32 AM
    Author     : Shazwan
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function() {   
                   
    });
    function dodacheck(val) {
             //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
             var v = $('#jenisPengenalan').val();

             if(v == 'B') {
                 var strPass = val;
                 var strLength = strPass.length;
                 //$('#kp').attr("maxlength","12");
                 if(strLength > 12) {
                     var tst = val.substring(0, (strLength) - 1);
                     $('#pihak.noPengenalan').val(tst);
                 }
                 var lchar = val.charAt((strLength) - 1);
                 if(isNaN(lchar)) {
                     //return false;
                     var tst = val.substring(0, (strLength) - 1);
                     $('#pihak.noPengenalan').val(tst);
                 }
             }//else{
             // $('#kp').attr("maxlength","30");
             // }
         }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form2" id="form2" beanclass="etanah.view.stripes.pelupusan.MaklumatPenggunaanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
    <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
    <s:hidden name="viewOnly" value="${actionBean.viewOnly}"/>
    <table width="90%" border="0" >
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><font color="red">*</font>Keluasan Penggunaan  </td>
            <td colspan="2">: 
                    <c:if test="${!actionBean.viewOnly}">
                        <s:text name="mohonHakmilik.luasTerlibat" maxlength=""/> 
                        <s:select name="luasterlibatUOM" style="width:150px" value="" id="luasterlibatUOM">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                        </s:select> 
                    </c:if> 
                    <c:if test="${actionBean.viewOnly}">
                        ${actionBean.mohonHakmilik.luasTerlibat} ${actionBean.luasTerlibatName}                       
                    </c:if>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><font color="red">*</font>Tujuan </td>
            <td colspan="2">: 
                <c:if test="${!actionBean.viewOnly}">
                        <s:select name="permohonan.sebab" id="permohonan.sebab" >
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="Bengkel">Bengkel</s:option>
                            <s:option value="Menara Telekomunikasi">Menara Telekomunikasi</s:option>
                            <s:option value="Perniagaan Kereta Terpakai">Perniagaan Kereta Terpakai</s:option>
                            <s:option value="Perusahaan Ringan">Perusahaan Ringan</s:option>
                            <s:option value="Pameran">Pameran</s:option>
                            <s:option value="Terminal">Terminal</s:option>
                            <s:option value="Lain-lain">Lain-lain</s:option>
                        </s:select>                    
                </c:if>
                <c:if test="${actionBean.viewOnly}">
                    ${actionBean.permohonan.sebab}                                        
                </c:if>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><font color="red">*</font>Jenis Pengenalan</td>
            <td colspan="2">:
                  <c:if test="${!actionBean.viewOnly}">
                        <s:select name="jenisPengenalan" id="jenisPengenalan">
                         <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiJenisPengenalanPelupusanGSA}" label="nama" value="kod"/>
                        </s:select>                                    
                  </c:if>
                  <c:if test="${actionBean.viewOnly}">
                       ${actionBean.jenisPengenalanName}               
                  </c:if>
            </td>
        </tr>
        <c:if test="${!actionBean.viewOnly}">
            <tr>
                <td>&nbsp;</td>
                <td><font color="red">*</font>No K/P / No.Syarikat </td>
                <td colspan="2">
                    : <s:text name="pihak.noPengenalan" size="40" maxlength="100" id="pihak.noPengenalan"/><font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><em>[780104069871]</em></font>&nbsp;<s:button name="cariPengguna" id="save" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </td>
            </tr>
            <c:if test="${actionBean.statusPguna}">
                <tr>
                    <td>&nbsp;</td>
                    <td><font color="red">*</font>Pengguna </td>
                    <td colspan="2">
                         : <s:text name="pihak.nama" size="50" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><font color="red">*</font>Alamat </td>
                    <td colspan="2">
                         : <s:text name="pihak.alamat1" size="50" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        &nbsp;&nbsp;<s:text name="pihak.alamat2" size="50" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        &nbsp;&nbsp;<s:text name="pihak.alamat3" size="50" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        &nbsp;&nbsp;<s:text name="pihak.alamat4" size="50" maxlength="100"/>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td><font color="red">*</font>Warganegara</td>
                    <td colspan="2">
                        : <s:select name="wargaNegara" id="wargaNegara">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </td>
                </tr>
            </c:if> 
        </c:if>
        <c:if test="${actionBean.viewOnly}">
                <tr>
                    <td>&nbsp;</td>
                    <td><font color="red">*</font>Pengguna </td>
                    <td colspan="2">
                         : ${actionBean.pihak.nama}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><font color="red">*</font>Alamat </td>
                    <td colspan="2">
                         : ${actionBean.pihak.alamat1}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        &nbsp;&nbsp;${actionBean.pihak.alamat2}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        &nbsp;&nbsp;${actionBean.pihak.alamat3}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        &nbsp;&nbsp;${actionBean.pihak.alamat4}
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td><font color="red">*</font>Warganegara</td>
                    <td colspan="2">
                        : ${actionBean.warganegaraName}
                    </td>
                </tr>
        </c:if>
    </table>
</s:form>