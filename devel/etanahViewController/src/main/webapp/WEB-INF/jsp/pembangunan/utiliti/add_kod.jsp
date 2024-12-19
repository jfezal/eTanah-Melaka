<%-- 
    Document   : add_kod
    Created on : Aug 1, 2013, 12:36:21 PM
    Author     : khairul.hazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utiliti Kod</title>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript">
            function tutup2() {
                self.opener.confirmRefresh();
                self.close();
            }
            
            function validate() {
                if ($("#kod").val() == "") {
                    alert("Sila masukkan Kod");
                    return false;
                }
                if ($("#nama").val() == "") {
                    alert("Sila masukkan Nama");
                    return false;
                }
                if ($("#aktif").val() == "") {
                    alert("Sila masukkan Kod Aktif");
                    return false;
                }
                if ($("#diMasukOleh").val() == "") {
                    alert("Sila masukkan Id Pengguna");
                    return false;
                }
                if ($("#tarikhMasuk").val() == "") {
                    alert("Sila masukkan Tarikh");
                    return false;
                }
                return true;
            }

            <%--function addrec() {
                if (confirm('Adakah anda pasti?')) {
                    var url = '${pageContext.request.contextPath}/pembangunan/utiliti/kodlist?tambahRecord';
                    $.get(url,
                    function(data) {
                        $('#page_div').html(data);           
                    }, 'html');                    
            <c:if test="${flag eq true}">
                        opener.confirmRefresh();
                        self.close();
            </c:if>
                    }
                } --%>

    function tutup() {
        self.close();
        <%--opener.confirmRefresh();--%>
    }
    function addrec(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    opener.confirmRefresh();
                    self.close();
                },'html');
                return true;
            }
        </script>
    <body>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <s:form beanclass="etanah.view.stripes.pembangunan.utiliti.ListKodUtil">
            <s:messages/>
            <s:errors/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <s:hidden name="nameTable" value="${nameTable}"/> 
                    <legend>
                        Tambah Kod
                    </legend>                    

                    <div align="center">
                        <table class="tablecloth">                            
                            <tr>
                                <td><font color="red">*</font>Nama :</td>
                                <td><s:text name="nama" id="nama" size="30"/> </td>
                            </tr>
                            <tr>
                                <td><font color="red">*</font>Aktif :</td>
                                <td><s:select name="aktif" id="aktif">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="Y">Aktif</s:option>
                                        <s:option value="T">Tidak Aktif</s:option>
                                    </s:select> </td>
                            </tr>
                            <%--<tr>
                                <td>Dimasukkan Oleh :</td>
                                <td><s:text name="diMasukOleh" id="diMasukOleh" size="30"/></td>
                            </tr>--%>

                            <c:if test="${actionBean.nameTable eq 'KOD_DUN'}">
                                <tr><td><font color="red">*</font>Parlimen :</td>
                                    <td><s:select name="kodKawasanParlimen"  id="kodKawasanParlimen" style="width:150px;">
                                            <s:option  value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.kodParimen}" label="nama" value="kod"/>
                                        </s:select> 
                                    </td></tr>
                                <tr><td><font color="red">*</font>Agensi :</td>
                                    <td><s:select name="agency"  id="agency" style="width:450px;">
                                            <s:option  value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodAgensi}" label="nama" value="kod"/>
                                        </s:select> 
                                    </td></tr> 
                                </c:if>
                                <c:if test="${actionBean.nameTable eq 'ADUN' }">
                                <tr><td><font color="red">*</font>Dun :</td>
                                    <td><s:select name="dun"  id="dun" style="width:150px;">
                                            <s:option  value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod"/>
                                        </s:select>
                                    </td></tr> 
                                <tr><td><font color="red">*</font>Daerah :</td>
                                    <td><s:select name="kD" id="kD" style="width:150px;">
                                            <s:option  value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.DL}" label="nama" value="kod"/>
                                        </s:select> 
                                    </td>
                                </tr>
                                <tr><td><font color="red">*</font>Gelaran :</td>
                                    <td><s:select name="kG" id="kG" style="width:150px;">
                                            <s:option  value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.GL}" label="nama" value="kod"/>
                                        </s:select> 
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red">*</font>Alamat1 :</td>
                                    <td><s:text name="alamat1" id="alamat1" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat2 :</td>
                                    <td><s:text name="alamat2" id="alamat2" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat3 :</td>
                                    <td><s:text name="alamat3" id="alamat3" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat4 :</td>
                                    <td><s:text name="alamat4" id="alamat4" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td><font color="red">*</font>Poskod :</td>
                                    <td><s:text name="poskod" id="poskod" size="30" maxlength="5"/></td>
                                </tr>
                                <tr>
                                    <td><font color="red">*</font>Negeri :</td>
                                    <td><s:select name="negeri">
                                            <s:option value="">Pilih ...</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No.Tel2 :</td>
                                    <td><s:text name="noTelefon2" id="noTelefon2" size="30" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td>No.Tel1 :</td>
                                    <td><s:text name="noTelefon1" id="noTelefon1" size="30" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td>emel :</td>
                                    <td><s:text name="emel" id="emel" size="30" maxlength="80"/></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.nameTable eq 'KOD_AGENSI' }">
                                <tr>
                                    <td><font color="red">*</font>Alamat1 :</td>
                                    <td><s:text name="alamat1" id="alamat1" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat2 :</td>
                                    <td><s:text name="alamat2" id="alamat2" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat3 :</td>
                                    <td><s:text name="alamat3" id="alamat3" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat4 :</td>
                                    <td><s:text name="alamat4" id="alamat4" size="30" maxlength="60"/></td>
                                </tr>
                                <tr>
                                    <td><font color="red">*</font>Poskod :</td>
                                    <td><s:text name="poskod" id="poskod" size="30" maxlength="5"/></td>
                                </tr>
                                <tr>
                                    <td><font color="red">*</font>Negeri :</td>
                                    <td><s:select name="negeri">
                                            <s:option value="">Pilih ...</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Kategori Agensi :</td>
                                    <td><s:select name="katgAgensi">
                                            <s:option value="">Pilih ...</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodKategoriAgensi}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No.Tel2 :</td>
                                    <td><s:text name="noTelefon2" id="noTelefon2" size="30" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td>No.Tel1 :</td>
                                    <td><s:text name="noTelefon1" id="noTelefon1" size="30" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td>emel :</td>
                                    <td><s:text name="emel" id="emel" size="30" maxlength="80"/></td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                    <center>
                        <%--<s:submit name="tambahRecord" id="simpan1" value="Simpan" class="btn" onclick="addrec();"/>--%>                    
                        <%--<s:submit name="tambahRecord" id="simpan1" value="Simpan" class="btn" onclick="tutup();"/>--%>
                        <s:button name="tambahRecord" id="simpan1" value="Simpan" class="btn" onclick="addrec(this.name,this.form);"/>
                        <s:button name="ttp" value="Tutup" class="btn" onclick="tutup();"/>
                    </center>
                </fieldset>
            </div>
        </s:form>
    </body>
</html>