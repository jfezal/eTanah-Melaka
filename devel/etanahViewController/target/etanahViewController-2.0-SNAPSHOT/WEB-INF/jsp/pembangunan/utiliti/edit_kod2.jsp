<%-- 
    Document   : edit_kod2
    Created on : Aug 1, 2013, 12:35:33 PM
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
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/css/formdesign.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">
            function tutup() {
                self.close();
                opener.confirmRefresh();
            }
            function kemaskini(event, f){                 
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
                    <s:hidden name="ntab" id="ntab"/>                  
                    <legend>
                        Edit Kod
                    </legend>                 

                    <div align="center">
                        <table class="tablecloth">
                            <tr>
                                <td>Kod :</td>
                                <td><s:text name="kod" id="kod" onblur="this.value=this.value.toUpperCase();" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td>Nama :</td>
                                <td><s:text name="nama" id="nama" size="30"/></td>
                            </tr>
                            <tr>
                                <td> Aktif :</td>
                                <td><s:select name="aktif" id="aktif">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="Y">Aktif</s:option>
                                        <s:option value="T">Tidak Aktif</s:option>
                                    </s:select></td>
                            </tr>
                            <%--<tr>
                                <td>Dikemaskini Oleh :</td>
                                <td><s:text name="diMasukOleh" id="diMasukOleh"/></td>
                            </tr>--%>
                            <c:if test="${actionBean.ntab eq 'KOD_DUN'}">
                                <tr><td>Parlimen :</td>
                                    <td><s:select name="kodKawasanParlimen" id="kodKawasanParlimen" style="width:150px;">
                                            <s:option  value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.kodParimen}" label="nama" value="kod"/>
                                        </s:select>
                                    </td></tr>
                                <tr><td>Agensi :</td>
                                    <td><s:select name="agency"  id="agency" style="width:450px;">
                                            <s:option  value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodAgensi}" label="nama" value="kod"/>
                                        </s:select> 
                                    </td></tr> 
                                </c:if>  
                                <c:if test="${actionBean.ntab eq 'ADUN'}">
                                <tr><td>Daerah :</td>
                                    <td><s:select name="kD" id="kD" value="${actionBean.kD}" style="width:150px;">
                                            <s:option  value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.DL}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr><td>Gelaran :</td>
                                    <td><s:select name="kG" id="kG" value="${actionBean.kG}" style="width:150px;">
                                            <s:option  value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.GL}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Alamat1 :</td>
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
                                    <td>Poskod :</td>
                                    <td><s:text name="poskod" id="poskod" size="30" maxlength="5"/></td>
                                </tr>
                                <tr>
                                    <td>Negeri :</td>
                                    <td><s:select name="negeri">
                                            <s:option value="">Pilih ...</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No.Tel1 :</td>
                                    <td><s:text name="noTelefon1" id="noTelefon1" size="30" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td>No.Tel2 :</td>
                                    <td><s:text name="noTelefon2" id="noTelefon2" size="30" maxlength="10"/></td>
                                </tr>
                                <tr>
                                    <td>emel :</td>
                                    <td><s:text name="emel" id="emel" size="30"/></td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.ntab eq 'KOD_AGENSI'}">
                                <tr>
                                    <td>Alamat1 :</td>
                                    <td><s:text name="alamat1" id="alamat1" size="30"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat2 :</td>
                                    <td><s:text name="alamat2" id="alamat2" size="30"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat3 :</td>
                                    <td><s:text name="alamat3" id="alamat3" size="30"/></td>
                                </tr>
                                <tr>
                                    <td>Alamat4 :</td>
                                    <td><s:text name="alamat4" id="alamat4" size="30"/></td>
                                </tr>
                                <tr>
                                    <td>Poskod :</td>
                                    <td><s:text name="poskod" id="poskod" size="30" maxlength="5"/></td>
                                </tr>
                                <tr>
                                    <td>Negeri :</td>
                                    <td><s:select name="negeri">
                                            <s:option value="">Pilih ...</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No.Tel1 :</td>
                                    <td><s:text name="noTelefon1" id="noTelefon1" size="30"/></td>
                                </tr>
                                <tr>
                                    <td>No.Tel2 :</td>
                                    <td><s:text name="noTelefon2" id="noTelefon2" size="30"/></td>
                                </tr>
                                <tr>
                                    <td>emel :</td>
                                    <td><s:text name="emel" id="emel" size="30"/></td>
                                </tr>
                            </c:if>
                        </table>
                    </div>

                    <center>                       
                        <s:button name="update" id="simpan1" value="Simpan" class="btn" onclick="kemaskini(this.name,this.form);"/>
                        <%--<c:if test="${actionBean.nameTable eq 'ADUN'}">
                        <s:submit name="updateAdun" id="simpan1" value="Simpan" class="btn" onclick="tutup()"/>  
                        </c:if>--%>
                    </center>
                    <br>
                    <br>
                </fieldset>
            </div>
        </s:form>
    </body>
</html>