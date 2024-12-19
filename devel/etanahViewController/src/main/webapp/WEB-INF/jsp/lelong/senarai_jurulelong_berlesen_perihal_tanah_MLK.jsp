<%-- 
    Document   : senarai_jurulelong_berlesen_perihal_tanah_MLK
    Created on : May 31, 2011, 1:53:01 AM
    Author     : mazurahayati.yusop
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
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/Memasukkan_Maklumat_JurulelongBerlesen?refreshPage2';
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
    
    function validate(){
        var a = ${A};
        var b = ${B};
        if(b){
            if($('#perihal').val() == ""){
                alert("Sila Masukkan Perihal Tanah");
                $('#perihal').focus();
                return false;
            }
        }
        
        if(a){
            var bil = $(".rowCount").length;
            for (var i = 0; i < bil; i++){
                if($('#perihal'+i).val() == ""){
                    alert("Sila Masukkan Perihal Tanah");
                    $('#perihal'+i).focus();
                    return false;
                }
            }   
        }
        return true;
    }


</script>       

<s:form action="" beanclass="etanah.view.stripes.lelong.MemasukkanMaklumatJurulelongBerlesenActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
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
                    <c:if test="${actionBean.mlk eq true}">
                        <c:if test="${B eq true}">
                            <p>
                                <label><font color="red">*</font> Perihal Tanah : </label>
                                <s:textarea id="perihal" name="perihal" cols="50" rows="5"/>

                            </p>
                        </c:if>

                        <c:if test="${A eq true}">
                            <div align="center">
                                <display:table class="tablecloth" name="${actionBean.lelonganList}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="IDHakmilik" class="rowCount" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                                    <display:column title="Perihal Tanah">
                                        <s:textarea id="perihal${line_rowNum - 1}" name="lelonganList[${line_rowNum - 1}].perihalTanah" cols="50" rows="5"/>
                                    </display:column>
                                </display:table>
                            </div>
                        </c:if>
                    </c:if>
                    <p align="center">
                        <s:button name="simpanPerihal" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
                        <c:if test="${actionBean.jual eq true}">
                            <s:button name="genReportJual" id="report" value="Perisytiharan Jualan" class="longBtn" onclick="showReport(this.name);"/>
                        </c:if>
                    </p>



                </c:if>

                <c:if test="${actionBean.flag eq true}">
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:if test="${actionBean.display ne false}">
                        <p><label>Jurulelong : </label>
                            Jurulelong Berlesen
                        </p><br>
                    </c:if>
                </c:if>
                <%-- <font color="red">* </font><font color="black">Sila Jana Dan Cetak Surat Perlantikan Jurulelong Berlesen</font><br>
             </c:if>
             <br>
             <div align="center" >
                 <display:table class="tablecloth" name="${actionBean.lelonganList3}"  cellpadding="0" cellspacing="0" id="line" requestURI="/lelong/Memasukkan_Maklumat_JurulelongBerlesen">
                     <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                     <display:column title="Nama Jurulelong" property="jurulelong.nama"/>
                     <display:column title="Jenis Pengenalan" property="jurulelong.jenisPengenalan.nama"/>
                     <display:column title="No Pengenalan" property="jurulelong.noPengenalan"/>
                     <display:column title="Alamat">${line.jurulelong.alamat1}
                         <c:if test="${line.jurulelong.alamat2 ne null}"> , </c:if>
                         ${line.jurulelong.alamat2}
                         <c:if test="${line.jurulelong.alamat3 ne null}"> , </c:if>
                         ${line.jurulelong.alamat3}
                         <c:if test="${line.jurulelong.alamat4 ne null}"> , </c:if>
                         ${line.jurulelong.alamat4}</display:column>
                     <display:column title="Poskod" property="jurulelong.poskod" />
                     <display:column title="Negeri" property="jurulelong.negeri.nama"/>
                     <display:column title="Cawangan" property="jurulelong.cawangan.name"/>
                     <display:column title="No Telefon Bimbit" property="jurulelong.noTelefon2"/>
                     <display:column title="Syarikat" property="jurulelong.sytJuruLelong.nama" class="nama"/>
                     <display:column title="No Telefon Syarikat" property="jurulelong.noTelefon1"/>
                 </display:table>
                 <br>--%>
                <%-- <c:if test="${actionBean.mlk eq true}">
                     <c:if test="${actionBean.display eq false}">
                         <p>
                             <s:button name="" value="Pilih" class="btn" onclick="pilih();"/>
                             <s:button name="batal" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                         </p>


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

                        </c:if>--%>
                <c:if test="${actionBean.mlk eq false}">
                    <c:if test="${actionBean.display eq false}">
                        <p>
                            <s:button name="" value="Pilih" class="btn" onclick="pilih();"/>
                            <s:button name="batal" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </p>
                        <c:if test="${jurulelong eq true}">
                            <p>
                                <s:button name="genReport2" id="report" value="Jana Notis & Surat" class="longBtn" onclick="showReport(this.name);"/>
                            </p>
                        </c:if>
                    </c:if>
                </c:if>
        </div>
    </c:if>
    <%-- </fieldset>
 </div>
</c:if>--%>
</s:form>
