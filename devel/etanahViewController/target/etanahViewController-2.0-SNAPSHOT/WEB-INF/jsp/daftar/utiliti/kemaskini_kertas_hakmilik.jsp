<%-- 
    Document   : kemaskini_kertas_hakmilik
    Created on : Nov 1, 2012, 3:48:40 AM
    Author     : Zulhazmi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <s:messages />
 <s:errors />       
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.daftar.utiliti.KemaskiniKertasHakmilik">
    
    <div class="subtitle" id="cari">
        <fieldset class="aras1">
            <legend>
                Kemaskini Kertas Hakmilik
            </legend>
            
                <p><label>Nama Pendaftar :</label>
                <%--Senarai Pengguna Melaka--%>
            
            <c:if test="${actionBean.kodNegeri eq '04'}">
                
                <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
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
                
            <p>
                <label>&nbsp;</label>
                <s:submit  name="searchPenyerahForEdit" value="Cari" class="btn" />
            </p>
        </fieldset>
    </div>
    <br/>
    
    
    <%--<c:if test="${fn:length(actionBean.senaraiHakmilikKertas)>0 }">--%>
    <div class="subtitle" id="hasilcarian">
        <fieldset class="aras1">
            <legend>
                Keputusan Carian Kertas Hakmilik
            </legend>
                
                <div class="content" align="center">
                
                <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiHakmilikKertas}" pagesize="10"
                               cellpadding="0" cellspacing="0" id="line"  excludedParams="selectedTab">
                    <%--<display:column title ="Pilih">
                        <div align="center">
                            <s:radio name="idHakmilikKertas" class="hakmilikKertas"
                                     id="${line_rowNum}" value="${line.idHakmilikKertas}" onchange="kemaskini(this.val)"/>
                        </div>
                    </display:column>--%>
                    
                    <display:column title="Bil"><div align="center">${line_rowNum}</div></display:column>
                    <display:column title="No. Siri Awal"><div align="center">${line.noAwal}</div></display:column> 
                    <display:column title="No. Siri Akhir"><div align="center">${line.noAkhir}</div></display:column> 
                    <display:column title="Bilangan Kertas"><div align="center">${line.bilangan}</div></display:column> 
                    <display:column title="Tarikh Ambil">                        
                        <div align="center">
                            <fmt:formatDate type="date"pattern="dd/MM/yyyy" value="${line.tarikhDiambil}"/>
                        </div>
                    </display:column>
                    <display:column title ="Kemaskini">
                        <s:link beanclass="etanah.view.daftar.utiliti.KemaskiniKertasHakmilik" event="kertasHakmilikForm"> 
                            <s:param name="idHakmilikKertas" value="${line.idHakmilikKertas}"/>
                            <div align="center">Kemaskini</div>
                        </s:link>
                    </display:column>
                </display:table>
            </div>
            <br/>
         <%--<c:if test="${fn:length(actionBean.senaraiHakmilikKertas)>0}">
                <p>
                    <label>&nbsp;</label><s:submit name="pilih" value="Pilih" class="btn"/>
                </p>
            </c:if>
            <%--<c:if test="${fn:length(actionBean.senaraiHakmilikKertas)>0}">
                <p>
                    <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihKertas();"/>
                </p>
            </c:if>--%>
        </fieldset>
    </div>
    
    
    
    <br/>
    
    <%--
    <div class="subtitle kemaskini">
        <c:if test="${hakmilikKertas ne null}">
        <s:hidden name="idHakmilikKertas" id="idHakmilikKertas"/>
        <fieldset class="aras1">
            <legend>
                Kemaskini Hakmilik Kertas
            </legend>
                
            <p> 
                <label>No. Siri Dari :</label>
                <s:text name="hakmilikKertas.noAwal"  style="width:10%"/><font size ="2" color ="black" > Hingga</font>
                <s:text name="hakmilikKertas.noAkhir" style="width:10%"/><font size ="2" color="red"> [Contoh: PDTMT7118900]</font>
                
            </p><br>
            
            <%--Jenis Kertas Melaka--%>
            <%--<c:if test="${actionBean.peng.negeri.kod eq '04'}">--%>
            <%--<c:if test="${actionBean.kodNegeri eq '04'}">
                <p><label>Jenis Kertas :</label>
                    <s:radio  name="hakmilikKertas.jenisKertas" value="DHDE" ></s:radio> DHDE
                    <s:radio  name="hakmilikKertas.jenisKertas" value="DHKE" ></s:radio> DHKE
                    </p>
            </c:if>
            <%--End Jenis Kertas Melaka--%>
            
            <%--<p><label>Tarikh Ambil :</label>
                <s:text name="hakmilikKertas.tarikhDiambil"  class="datepicker"
                        onblur="editDateBlur(this, 'DD/MM/YYYY')"
                        onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                        onkeyup="return editDate(this, 'DD/MM/YYYY', event);" id="tarikhAmbil"/> <font size="1" color="red">[hh/bb/tttt]</font>
            </p>
            
            <p><label>Nama Pengguna :</label>

                <%--Senarai Pengguna Melaka--%>
                <%--<c:if test="${actionBean.peng.negeri.kod eq '04'}">--%>
                <%--<c:if test="${actionBean.kodNegeri eq '04'}">

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPendaftar}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
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
                <%--<c:if test="${actionBean.kodNegeri eq '05'}">
                
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPendaftar}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '05'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftar05}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>  
                </c:if>
                <%--End Senarai Pengguna Negeri 9--%>
            <%--</p>           
            
            <br>            
            <p>
                <label>&nbsp;</label><s:submit name="update" value="Simpan" class="btn"/>
                <s:submit name="kembali" value="Batal" class="btn"/>
            </p>
        </fieldset>
        </c:if>
    </div>--%>
   
</s:form>
