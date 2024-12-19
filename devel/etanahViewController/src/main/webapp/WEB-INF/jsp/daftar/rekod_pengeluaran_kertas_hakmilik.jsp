<%-- 
    Document   : rekod_pengeluaran_kertas_hakmilik
    Created on : Oct 29, 2012, 11:57:55 AM
    Author     : Zulhazmi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
    function clearForm(f) {
        $(f).clearForm();
    }
    function clearText1(id) {
        $("#"+id+" input:text").each( function(el) {
            $(this).val('');
        });
        
        $("#" + id +" select").each( function(el) {
            $(this).val('0');
        });
        reset1();
    }
    
    function clickclear() {
    form1.noAwal.value = "";
    form1.noAkhir.value = "";
    form1.jenisKertas.value = "";
    form1.tarikhAmbil.value = "";
    form1.noAwal.value = "";
    form1.idPengguna.value = "";
   
}
</script>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
    </head>
        
    <body>
       <s:messages />
        <s:errors />
        <s:form id="kertas_hakmilik" beanclass="etanah.view.daftar.utiliti.PengeluaranKertasHakmilik">
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />  
       <fieldset class="Aras1">
            <legend>Rekod Pengeluaran Kertas Hakmilik</legend>
     
        <p> 
            <label>No. Siri Dari :</label>
            <s:text id = "noAwal" name="noAwal" style="width:10%"/><font size ="2" color ="black" > Hingga</font>
            <s:text id = "noAkhir" name="noAkhir" style="width:10%"/><font size ="2" color="red"> [Contoh: PDTMT7118900]</font>
            
        </p><br>
      
        <%--Jenis Kertas Melaka--%>
        <%--<c:if test="${actionBean.peng.negeri.kod eq '04'}">--%>
        <%--<c:if test="${actionBean.kodNegeri eq '04'}">
        <p><label>Jenis Kertas :</label>
            <s:radio name="jenisKertas" value="DHDE" ></s:radio> DHDE
            <s:radio name="jenisKertas" value="DHKE" ></s:radio> DHKE
        </p>
        </c:if>
        <%--End Jenis Kertas Melaka--%>
            
        <p><label>Tarikh Ambil :</label>
            <s:text name="tarikhAmbil" class="datepicker"
                    onblur="editDateBlur(this, 'DD/MM/YYYY')"
                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="2" color="red">[hh/bb/tttt]</font>
        </p>
       
        <p><label>Nama Pendaftar :</label>
            
            <%--Senarai Pengguna Melaka--%>
            <%--<c:if test="${actionBean.peng.negeri.kod eq '04'}">--%>
            <c:if test="${actionBean.kodNegeri eq '04'}">
                
                <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                    <s:select name ="idPengguna">
                        <s:option value = "0">Sila Pilih</s:option>
                         <c:forEach items="${listUtil.senaraiPendaftar}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                
                <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>

                <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>

                <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                
            </c:if>
            <%--End Senarai Pengguna Melaka--%>
            
            
            <%--Negeri 9--%>
            <%--<c:if test="${actionBean.peng.negeri.kod eq '05'}">--%>
            <c:if test="${actionBean.kodNegeri eq '05'}">
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftar}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '05'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftar05}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>  
            </c:if>
            <%--End Senarai Pengguna Negeri 9--%>
            
        </p>           
        <br>
            
        <div>
            <p align="center">
                      
                        <s:submit name="saveHakmilikKertas" value="Simpan" class="btn"/>
                        <s:submit name="reset" value="Isi Semula" class="btn" onclick="test(this.form);"/>
                       
            </p><br>   
           </div>  
              </fieldset>  
    </s:form>
</body>
</html>
