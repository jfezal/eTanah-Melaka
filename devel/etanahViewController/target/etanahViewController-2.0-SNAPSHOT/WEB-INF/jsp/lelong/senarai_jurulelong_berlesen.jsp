 <%-- 
    Document   : senarai_jurulelong_berlesen
    Created on : Apr 7, 2010, 10:38:36 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function showReport(val){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/Memasukkan_Maklumat_JurulelongBerlesen?'+val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
            },
            success : function(data) {
                if(data.indexOf('Laporan telah dijana')==0){
                    alert(data);
                    $('#folder').click();
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }

    function pilih(){
        window.open("${pageContext.request.contextPath}/lelong/Memasukkan_Maklumat_JurulelongBerlesen?getSenaraiJurulelongBerlesan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=1000,height=1200");
    }

    function refreshPage(){
        var url =  '${pageContext.request.contextPath}/lelong/Memasukkan_Maklumat_JurulelongBerlesen?refreshPage2';
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        },'html');
    }

    function refreshPagePilih(f){
        var queryString = $(f).formSerialize()
        var url = '${pageContext.request.contextPath}/lelong/Memasukkan_Maklumat_JurulelongBerlesen?refreshPage2'+queryString;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    $(document).ready(function() {
        $('#lelong').hide();

    });
    function changeLain(value){
        if(value=="lelong")
            $('#lelong').show();
        else
            $('#lelong').hide();


    }


</script>       

<s:form name="form" beanclass="etanah.view.stripes.lelong.MemasukkanMaklumatJurulelongBerlesenActionBean">
    <s:errors/>
    <s:messages/>
    <c:if test="${actionBean.button eq false}">
        <div class="subtitle" >
            <fieldset class="aras1">

                <legend>
                    Perlantikan Jurulelong
                </legend>

                <c:if test="${actionBean.flag eq false}">

                    <br>
                    <p><label>Jurulelong : </label>
                        Pentadbir Tanah
                    </p><br>
                    <br>
                    <c:if test="${actionBean.mlk eq false}">
                        <p align="center">
                            <s:button name="genReport3" id="report" value="Notis Lelongan" class="longBtn" onclick="showReport(this.name);"/>
                        </p>
                    </c:if>

                    <c:if  test="${actionBean.show eq false}">
                        <p align="center">
                            <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                        </p>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.flag eq true}">
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:if test="${actionBean.display ne false}">
                        <font color="red">* </font><font color="black">Sila Jana Dan Cetak Surat Perlantikan Jurulelong Berlesen</font><br>
                    </c:if>
                    <br>
                    <c:if test="${actionBean.mlk eq true}">
                        <c:if test="${actionBean.stageJurulelongN9 eq false}">
                            <p><label>Jurulelong : </label>
                                Jurulelong Berlesen
                            </p><br>
                        </c:if>
                        <c:if test="${actionBean.stageJurulelongN9 eq true}">
                               <display:table class="tablecloth" name="${actionBean.lelonganList3}"  cellpadding="0" cellspacing="0" id="line2" requestURI="/lelong/Memasukkan_Maklumat_JurulelongBerlesen">
                                    <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                                    <display:column title="Nama Jurulelong" property="jurulelong.nama"/>
                                    <display:column title="Jenis Pengenalan" property="jurulelong.jenisPengenalan.nama"/>
                                    <display:column title="No Pengenalan" property="jurulelong.noPengenalan"/>
                                    <display:column title="Alamat">${line2.jurulelong.alamat1}
                                        <c:if test="${line2.jurulelong.alamat2 ne null}"> , </c:if>
                                        ${line2.jurulelong.alamat2}
                                        <c:if test="${line2.jurulelong.alamat3 ne null}"> , </c:if>
                                        ${line2.jurulelong.alamat3}
                                        <c:if test="${line.jurulelong.alamat4 ne null}"> , </c:if>
                                        ${line2.jurulelong.alamat4}</display:column>
                                    <display:column title="Poskod" property="jurulelong.poskod" />
                                    <display:column title="Negeri" property="jurulelong.negeri.nama"/>
                                    <display:column title="Cawangan" property="jurulelong.cawangan.name"/>
                                    <display:column title="No Telefon Bimbit" property="jurulelong.noTelefon2"/>
                                    <display:column title="Syarikat" property="jurulelong.sytJuruLelong.nama" class="nama"/>
                                    <display:column title="No Telefon Syarikat" property="jurulelong.noTelefon1"/>
                                </display:table>
                            </c:if>
                        </c:if>

                        <c:if test="${actionBean.mlk eq false}">
                                <display:table class="tablecloth" name="${actionBean.lelonganList2}"  cellpadding="0" cellspacing="0" id="line" requestURI="/lelong/Memasukkan_Maklumat_JurulelongBerlesen">
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Syarikat" property="sytJuruLelong.nama"/>
                                    <display:column title="Jenis Pengenalan" property="sytJuruLelong.jenisPengenalan.nama"/>
                                    <display:column title="No Pengenalan" property="sytJuruLelong.noPengenalan"/>
                                    <display:column title="Alamat">${line.sytJuruLelong.alamat1}
                                        <c:if test="${line.sytJuruLelong.alamat2 ne null}"> , </c:if>
                                        ${line.sytJuruLelong.alamat2}
                                        <c:if test="${line.sytJuruLelong.alamat3 ne null}"> , </c:if>
                                        ${line.sytJuruLelong.alamat3}
                                        <c:if test="${line.sytJuruLelong.alamat4 ne null}"> , </c:if>
                                        ${line.sytJuruLelong.alamat4}</display:column>
                                    <display:column title="Poskod" property="sytJuruLelong.poskod" />
                                    <display:column title="Negeri" property="sytJuruLelong.negeri.nama"/>
                                    <display:column title="Cawangan" property="sytJuruLelong.cawangan.name"/>
                                    <display:column title="No Telefon Bimbit" property="sytJuruLelong.noTelefon2"/>
                                    <display:column title="No Telefon Syarikat" property="sytJuruLelong.noTelefon1"/>
                                </display:table>
                            </c:if>
                            <br>
                            <c:if test="${actionBean.mlk eq true}">
                                <c:if test="${actionBean.display eq false}">
                                    <c:if test="${actionBean.stageJurulelongN9 eq true}">
                                        <p>
                                            <s:button name="" value="Pilih" class="btn" onclick="pilih();"/>
                                            <s:button name="batal" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                                        </p>
                                    </c:if>


                                </c:if>
                                <c:if  test="${actionBean.show eq false}">
                                    <br><br>
                                    <p>
                                        <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                    </p>
                                </c:if>
                                <c:if test="${actionBean.display ne false}">
                                    <p>
                                        <s:button name="genReport" id="report" value="Surat Perlantikan" class="longBtn" onclick="showReport(this.name);"/>
                                    </p>
                                </c:if>

                            </c:if>
                            <c:if test="${actionBean.mlk eq false}">
                                <c:if test="${actionBean.display eq false}">
                                    <p>
                                        <s:button name="" value="Pilih" class="btn" onclick="pilih();"/>
                                        <s:button name="batal" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                                    </p>
                                    <c:if  test="${actionBean.show eq false}">
                                        <p>
                                            <s:button name="kembali" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                        </p>
                                    </c:if>
                                    <c:if test="${jurulelong eq true}">
                                        <p>
                                            <s:button name="genReport2" id="report" value="Jana Notis & Surat" class="longBtn" onclick="showReport(this.name);"/>
                                        </p>
                                    </c:if>
                                </c:if>
                            </c:if>
                    </c:if>
            </fieldset>
        </div>
    </c:if>
    <br/>
    <c:if test="${fn:length(actionBean.lelonganListJLB)>0}">
        <fieldset class="aras1">
            <br>
            <legend>
                Sejarah Lelongan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.lelonganListJLB}" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Lelong" sortable="true">${line.idLelong}</display:column>
                    <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    <display:column title="Status Lelongan">                    
                        <c:if test="${line.bil eq '1'}">
                                Kali Pertama
                        </c:if>
                        <c:if test="${line.bil eq '2'}">
                                Kali Kedua
                        </c:if>
                        <c:if test="${line.bil eq '3'}">
                                Kali Ketiga
                        </c:if></display:column>
                    <display:column title="Syarikat" property="sytJuruLelong.nama"/>
                    <display:column title="Jenis Pengenalan" property="sytJuruLelong.jenisPengenalan.nama"/>
                    <display:column title="No Pengenalan" property="sytJuruLelong.noPengenalan"/>
                    <display:column title="Alamat">${line.sytJuruLelong.alamat1}
                        <c:if test="${line.sytJuruLelong.alamat2 ne null}"> , </c:if>
                        ${line.sytJuruLelong.alamat2}
                        <c:if test="${line.sytJuruLelong.alamat3 ne null}"> , </c:if>
                        ${line.sytJuruLelong.alamat3}
                        <c:if test="${line.sytJuruLelong.alamat4 ne null}"> , </c:if>
                        ${line.sytJuruLelong.alamat4}</display:column>
                    <display:column title="Poskod" property="sytJuruLelong.poskod" />
                    <display:column title="Negeri" property="sytJuruLelong.negeri.nama"/>
                    <display:column title="No Telefon Bimbit" property="sytJuruLelong.noTelefon2"/>
                    <display:column title="No Telefon Syarikat" property="sytJuruLelong.noTelefon1"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
</s:form>


